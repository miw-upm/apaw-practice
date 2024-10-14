package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VideoGameRepository extends MongoRepository<VideoGamerEntity, String> {
    Optional<VideoGamerEntity> findByVideoGameAlias(String videoGameAlias);
}
