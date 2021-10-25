package es.upm.miw.apaw_practice.adapters.mongodb.gym.daos;
import es.upm.miw.apaw_practice.adapters.mongodb.gym.entities.CoachEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CoachRepository extends MongoRepository<CoachEntity, String> {
    Optional<CoachEntity> findByDni(String dni);

    void deleteByDni(String dni);
}
