package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
