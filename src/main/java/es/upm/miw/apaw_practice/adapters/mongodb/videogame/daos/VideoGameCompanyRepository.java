package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGameCompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoGameCompanyRepository extends MongoRepository<VideoGameCompanyEntity, String> {
}
