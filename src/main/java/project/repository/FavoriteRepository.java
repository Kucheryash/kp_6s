package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Car;
import project.entity.Favorite;
import project.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findAllByUser(User user);
    List<Favorite> findByCarAndUser(Car car, User user);
    List<Favorite> findAllByDealer(Long id);
}
