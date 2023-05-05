package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import project.entity.Car;
import project.entity.Country;
import project.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByDealer(User user);
    List<Car> findAll();
    List<Car> findByBrandAndModelAndPriceBetweenAndTransmissionAndBodyAndYear_of_issueBetweenAndEngine_typeAndDriveAndVolumeBetween(
            String brand, String model, Integer minPrice, Integer maxPrice, String transmission, String body,
            Integer minYear, Integer maxYear, String engineType, String drive, Double minVolume, Double maxVolume);
}
