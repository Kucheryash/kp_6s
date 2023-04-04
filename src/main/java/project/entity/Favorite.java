package project.entity;

import jakarta.persistence.*;

@Entity(name = "favorites")
public class Favorite {
    @Column(name = "id_favorite")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_fav_id_user"))
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car", nullable = false, foreignKey = @ForeignKey(name = "fk_fav_id_car"))
    private Car car;

    public Favorite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User id_user) {
        this.user = id_user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car id_car) {
        this.car = id_car;
    }
}
