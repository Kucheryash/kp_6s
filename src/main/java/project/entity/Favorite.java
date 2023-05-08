package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "favorites")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Favorite {
    @Column(name = "id_favorite")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_fav_id_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_car", nullable = false, foreignKey = @ForeignKey(name = "fk_fav_id_car"))
    private Car car;

    @Column(nullable = false)
    private Long dealer;
}
