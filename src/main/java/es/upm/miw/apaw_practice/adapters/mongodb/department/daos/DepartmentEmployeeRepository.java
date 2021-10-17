package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentEmployeeRepository extends MongoRepository<DepartmentEmployeeEntity, String> {
}
