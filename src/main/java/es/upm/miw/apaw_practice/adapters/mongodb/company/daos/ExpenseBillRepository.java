package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ExpenseBillEntity;
import es.upm.miw.apaw_practice.domain.models.company.ExpenseBill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ExpenseBillRepository extends MongoRepository<ExpenseBillEntity, String> {

    @Query("{ 'departments.location': ?0 }")
    List<ExpenseBillEntity> findAllByLocation(String location);
    @Query("{ 'description': ?0 }")
    List<ExpenseBillEntity> findByDescription(String description);

}
