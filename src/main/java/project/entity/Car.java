package project.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity(name = "cars")
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

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_car) {
        this.id = id_car;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getYear_of_issue() {
        return year_of_issue;
    }

    public void setYear_of_issue(int year_of_issue) {
        this.year_of_issue = year_of_issue;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getDealer() {
        return dealer;
    }

    public void setDealer(User dealer_id) {
        this.dealer = dealer_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country_id) {
        this.country = country_id;
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
