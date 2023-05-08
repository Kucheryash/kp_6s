package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Column(name = "id_car")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false, name = "year_of_issue")
    private int year;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false, name = "engine_type")
    private String engine;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private String drive;

    @Column(nullable = false)
    private int mileage;

    @Column(length = 65535)
    private String description;

    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_car_id_user"))
    private User dealer;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_car_id_country"))
    private Country country;

    @OneToMany(mappedBy= "car")
    private List<Comment> comments;

    @OneToMany(mappedBy= "car")
    private List<Favorite> favorites;

    @Column
    private int fav;

    @OneToOne
    @JoinColumn(name = "image", foreignKey = @ForeignKey(name = "fk_car_image"))
    private Image image;

}
