package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.EmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {
}
