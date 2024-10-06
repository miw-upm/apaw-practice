package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlayServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PlayServiceRepository extends MongoRepository<PlayServiceEntity, String> {
    Optional<PlayServiceEntity> findByPlayServiceId(Integer playServiceId);
}
