package es.upm.miw.apaw.adapters.mongodb.music.daos;

import es.upm.miw.apaw.adapters.mongodb.music.entities.StyleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StyleRepository extends MongoRepository<StyleEntity, String> {
}