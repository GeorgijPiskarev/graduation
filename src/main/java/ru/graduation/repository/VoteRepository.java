package ru.graduation.repository;

import org.springframework.stereotype.Repository;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudVoteRepository crudVoteRepository;

    public VoteRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository, CrudVoteRepository crudVoteRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudVoteRepository = crudVoteRepository;
    }

    public void save(int userId, int restaurantId) {
        Vote vote = new Vote();
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        crudVoteRepository.save(vote);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        crudVoteRepository.save(vote);
    }

    public Vote getByUser(int userId, LocalDate date) {
        return crudVoteRepository.getByUserIdAndDate(userId, date);
    }

    public List<Vote> getByRestaurant(int restaurantId, LocalDate date) {
        return crudVoteRepository.getByRestaurantIdAndDate(restaurantId, date);
    }
}
