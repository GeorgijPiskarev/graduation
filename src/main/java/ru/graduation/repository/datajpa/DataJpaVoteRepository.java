package ru.graduation.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    public static final LocalTime END = LocalTime.of(23, 0);
    public static final LocalTime START = LocalTime.of(0, 0);

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudVoteRepository crudVoteRepository;

    public DataJpaVoteRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository, CrudVoteRepository crudVoteRepository) {
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

    @Override
    @Transactional
    public void vote(int userId, int restaurantId) {
        Vote vote = getByUser(userId, LocalDate.now());
        if (vote == null) {
            save(userId, restaurantId);
        } else {
            if (LocalTime.now().isAfter(END) && LocalTime.now().isBefore(START)) {
                throw new UnsupportedOperationException();
            }
            update(vote, userId, restaurantId);
        }
    }

    @Override
    public Vote getByUser(int userId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return crudVoteRepository.getByUserIdAndDate(userId, date);
    }

    @Override
    public List<Vote> getByUser(int userId) {
        return crudVoteRepository.getByUserId(userId);
    }

    @Override
    public List<Vote> getByRestaurant(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return crudVoteRepository.getByRestaurantIdAndDate(restaurantId, date);
    }
}
