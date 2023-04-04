package project.entity;

import jakarta.persistence.*;

@Entity(name = "sellers")
public class Seller {
    @Column(name = "id_seller")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "email", nullable = false, foreignKey = @ForeignKey(name = "fk_seller_email"))
    private User email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_seller_id_user"))
    private User user;

    public Seller() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_seller) {
        this.id = id_seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User id_user) {
        this.user = id_user;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }
}
