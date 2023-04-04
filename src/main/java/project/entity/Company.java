package project.entity;

import jakarta.persistence.*;

@Entity(name = "companies")
public class Company {
    @Id
    @Column(name = "id_company")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "email", nullable = false, foreignKey = @ForeignKey(name = "fk_company_email"))
    private User email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_company_id_user"))
    private User user;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_company) {
        this.id = id_company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
