package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=?1"),
        @NamedQuery(name = Restaurant.GET, query = "SELECT r FROM Restaurant r WHERE r.id=?1")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    public static final String DELETE = "Restaurant.delete";
    public static final String GET = "Restaurant.get";

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @OrderBy("name ASC")
    @JsonManagedReference
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.name = name;
        this.dishes = dishes;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDishes());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
