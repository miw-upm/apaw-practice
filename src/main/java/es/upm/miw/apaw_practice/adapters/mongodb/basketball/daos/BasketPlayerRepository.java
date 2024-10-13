package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketPlayerEntity;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketPlayerRepository extends MongoRepository<BasketPlayerEntity, String> {
    Optional<BasketPlayerEntity> findByDni(String dni);
}
