package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.Key;

import java.util.Optional;

@Repository
public interface KeyRepository extends CrudRepository<Key, Long> {
     Optional<Key> findByLoginAndPassword(String login, String password);
}
