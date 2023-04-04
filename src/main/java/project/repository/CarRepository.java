package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

}
