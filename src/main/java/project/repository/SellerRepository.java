package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
