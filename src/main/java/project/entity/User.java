package project.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User {
    @Column(name = "id_user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy= "dealer")
    private List<Car> cars;

    @OneToMany(mappedBy= "user")
    private List<Comment> comments;

    @OneToMany(mappedBy= "user")
    private List<Favorite> favorites;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_user) {
        this.id = id_user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
}
