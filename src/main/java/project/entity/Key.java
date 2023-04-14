package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Key{
    @Column(name = "id_key")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int role;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_key_id_user"))
    private User user;


}
