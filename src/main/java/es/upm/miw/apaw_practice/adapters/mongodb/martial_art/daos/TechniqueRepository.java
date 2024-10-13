package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.TechniqueEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TechniqueRepository extends MongoRepository<TechniqueEntity, String> {

    Optional<TechniqueEntity> findByName(String name);
}
