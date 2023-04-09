package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @Column(name = "id_image")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content_type;

    @Column(nullable = false)
    private boolean is_preview;

    @Lob
    @Column(nullable = false)
    private byte[] bytes;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", nullable = false, foreignKey = @ForeignKey(name = "fk_images_id_car"))
    private Car car;

}
