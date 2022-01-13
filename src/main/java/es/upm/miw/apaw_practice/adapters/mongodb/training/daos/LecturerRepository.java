package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LecturerRepository extends MongoRepository<LecturerEntity, String> {
    Optional<LecturerEntity> findByDni(String dni);
}
