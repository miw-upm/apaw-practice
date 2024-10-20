package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConsoleRepository extends MongoRepository<ConsoleEntity, String> {
    Optional<ConsoleEntity> findByConsoleReference(String consoleReference);
}