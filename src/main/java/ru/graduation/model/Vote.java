package ru.graduation.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Vote.GET_BY_RESTAURANT_AND_DATE, query = "SELECT v FROM Vote v WHERE v.restaurant.id = ?1 AND v.date = ?2"),
        @NamedQuery(name = Vote.GET_BY_USER_AND_DATE, query = "SELECT v FROM Vote v WHERE v.user.id = ?1 AND v.date = ?2")
})
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    public static final String GET_BY_RESTAURANT_AND_DATE = "Vote.getByRestaurantIdAndDate";
    public static final String GET_BY_USER_AND_DATE = "Vote.getByUserIdAndDate";

    @Column(name = "date", nullable = false, columnDefinition = "date default today()")
    @NotNull
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getUser(), vote.getRestaurant(), vote.getDate());
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate date) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
