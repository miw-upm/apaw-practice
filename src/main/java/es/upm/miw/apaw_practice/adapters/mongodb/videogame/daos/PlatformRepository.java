package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;


import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlatformEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlatformRepository extends MongoRepository<PlatformEntity, String> {
}
