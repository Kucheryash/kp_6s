package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
