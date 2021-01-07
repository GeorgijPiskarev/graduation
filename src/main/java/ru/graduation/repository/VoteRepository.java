package ru.graduation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class VoteRepository {
    public static final LocalTime END = LocalTime.of(11, 0);
    public static final LocalTime START = LocalTime.of(0, 0);

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudVoteRepository crudVoteRepository;

    public VoteRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository, CrudVoteRepository crudVoteRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudVoteRepository = crudVoteRepository;
    }

    private void save(int userId, int restaurantId) {
        Vote vote = new Vote();
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        crudVoteRepository.save(vote);
    }

    private void update(Vote vote, int userId, int restaurantId) {
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        crudVoteRepository.save(vote);
    }

    @Transactional
    public void vote(int userId, int restaurantId) {
        Vote vote = getByUser(userId, LocalDate.now());
        if (vote == null) {
            save(userId, restaurantId);
        } else {
            if (LocalTime.now().isAfter(END) && LocalTime.now().isBefore(START)) {
                throw new UnsupportedOperationException("Your vote can no longer be changed");
            }
            update(vote, userId, restaurantId);
        }
    }

    public Vote getByUser(int userId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return crudVoteRepository.getByUserIdAndDate(userId, date);
    }

    public List<Vote> getByRestaurant(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return crudVoteRepository.getByRestaurantIdAndDate(restaurantId, date);
    }

    public Vote get(int id) {
        return checkNotFoundWithId(crudVoteRepository.findById(id).orElse(null), id);
    }
}
