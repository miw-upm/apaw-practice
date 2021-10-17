package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {
}
