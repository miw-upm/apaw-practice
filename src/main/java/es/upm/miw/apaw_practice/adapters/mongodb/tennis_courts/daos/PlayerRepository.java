package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.PlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface PlayerRepository extends MongoRepository<PlayerEntity, String> {

    Optional<PlayerEntity> findByDni(String dni);

    Stream<PlayerEntity> findByName(String name);
}
