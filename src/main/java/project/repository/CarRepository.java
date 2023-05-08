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
    List<Car> findByBrandAndModelAndPriceBetween(String brand, String model, Integer minPrice, Integer maxPrice);
}
