package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
