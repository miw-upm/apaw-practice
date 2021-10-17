package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;


import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsoleRepository extends MongoRepository<ConsoleEntity, String> {
}
