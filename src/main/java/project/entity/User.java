package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User{
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

}
