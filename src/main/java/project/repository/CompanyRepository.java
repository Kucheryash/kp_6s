package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
