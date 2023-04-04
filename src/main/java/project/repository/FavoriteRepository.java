package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.Favorite;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

}
