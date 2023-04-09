package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_id_car"))
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_id_user"))
    private User user;

}
