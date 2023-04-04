package project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.entity.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

}
