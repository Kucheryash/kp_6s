package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {

}
