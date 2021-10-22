package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VideoGameRepository extends MongoRepository<VideoGameEntity, String> {
    Optional<VideoGameEntity> findByTitle(String title);

    int deleteByTitle(String title);
}
