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

    @Column(nullable = false)
    private int year_of_issue;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false)
    private String engine_type;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private String drive;

    @Column(nullable = false)
    private int mileage;

    @Column(length = 65535)
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dealer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_car_id_user"))
    private User dealer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "fk_car_id_country"))
    private Country country;

    @OneToMany(mappedBy= "car")
    private List<Comment> comments;

    @OneToMany(mappedBy= "car")
    private List<Favorite> favorites;

    @OneToMany(mappedBy= "car")
    private List<Image> images;

}
