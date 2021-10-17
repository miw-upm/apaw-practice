package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.ManagerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagerRepository extends MongoRepository<ManagerEntity, String> {
}
