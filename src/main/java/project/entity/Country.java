package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country {
    @Column(name = "id_country")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy= "country")
    private List<Car> cars;

}
