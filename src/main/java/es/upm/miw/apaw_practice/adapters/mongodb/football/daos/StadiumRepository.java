package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.StadiumEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StadiumRepository extends MongoRepository<StadiumEntity, String> {
    Optional<StadiumEntity> findByCity(String city);
}
