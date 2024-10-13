package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.StyleEntity;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StyleRepository extends MongoRepository<StyleEntity, String> {

    Optional<StyleEntity> findByName(String name);

    void deleteByname(String name);
}
