package ru.graduation.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @Column(name = "day", nullable = false, columnDefinition = "date default today()")
    @NotNull
    private LocalDate day = LocalDate.now();

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
        this(vote.getId(), vote.getUser(), vote.getRestaurant(), vote.getDay());
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate date) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.day = date;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate date) {
        this.day = date;
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
                ", date=" + day +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
