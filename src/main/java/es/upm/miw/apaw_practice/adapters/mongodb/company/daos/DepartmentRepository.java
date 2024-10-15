package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.DepartmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {

    Optional<DepartmentEntity> findByDepartmentName(String departmentName);
}