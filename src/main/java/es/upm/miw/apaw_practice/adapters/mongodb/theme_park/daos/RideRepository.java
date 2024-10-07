package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.TagEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.RideEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RideRepository extends MongoRepository<RideEntity, String> {
    Optional<RideEntity> findByName(String name);
}
