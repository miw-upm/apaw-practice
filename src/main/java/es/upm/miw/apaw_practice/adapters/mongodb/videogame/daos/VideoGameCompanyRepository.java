package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameCompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VideoGameCompanyRepository extends MongoRepository<VideoGameCompanyEntity, String> {
    Optional<VideoGameCompanyEntity> findByName(String name);
}
