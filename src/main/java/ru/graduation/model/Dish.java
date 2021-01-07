package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=?1 AND d.restaurant.id=?2"),
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d WHERE d.restaurant.id=?1 AND d.date=?2"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d WHERE d.id=?1 AND d.restaurant.id=?2")
})
@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {
    public static final String GET_ALL = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET = "Dish.get";

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false, columnDefinition = "date default today()")
    @NotNull
    private LocalDate date = LocalDate.now();

    public Dish() {
    }

    public Dish(Integer id, String name, int price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                ", date=" + date +
                '}';
    }
}
