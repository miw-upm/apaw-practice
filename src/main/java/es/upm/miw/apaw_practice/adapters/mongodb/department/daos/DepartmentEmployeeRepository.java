package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartmentEmployeeRepository extends MongoRepository<DepartmentEmployeeEntity, String> {
    Optional<DepartmentEmployeeEntity> findByDni(String dni);
}
