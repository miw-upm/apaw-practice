package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.ManagerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManagerRepository extends MongoRepository<ManagerEntity, String> {
    Optional<ManagerEntity> findByEmail(String email);

    int deleteByEmail(String email);
}
