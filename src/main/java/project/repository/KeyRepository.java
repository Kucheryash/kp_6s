package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Key;
import project.entity.User;

import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<Key, Long> {
     Optional<Key> findByLoginAndPassword(String login, String password);
     Optional<Key> findByUser(User user);
}
