package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.DepartmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {

    List<DepartmentEntity> findByDepartmentName(String departmentName);

}