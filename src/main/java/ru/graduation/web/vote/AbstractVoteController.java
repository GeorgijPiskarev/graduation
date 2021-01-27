package ru.graduation.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;
import ru.graduation.util.exception.OutOfTimeException;
import ru.graduation.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class AbstractVoteController {
    public static final LocalTime END = LocalTime.of(11, 0);

    public static LocalTime NOW = LocalTime.now();

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository voteRepository;

    public void vote(int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("vote for restaurant {} by user {}", restaurantId, userId);
        Vote vote = voteRepository.getByUser(userId, LocalDate.now());
        if (vote == null) {
            voteRepository.save(userId, restaurantId);
        } else {
            if (NOW.isAfter(END)) {
                throw new OutOfTimeException("Your vote can no longer be changed");
            }
            voteRepository.update(vote, userId, restaurantId);
        }
    }

    public List<Vote> getTodayVotes(int restaurantId) {
        log.info("get today's votes for restaurant {}", restaurantId);
        return voteRepository.getByRestaurant(restaurantId, LocalDate.now());
    }
}
