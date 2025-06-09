package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreeningEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScreeningRepository extends MongoRepository<ScreeningEntity, String> {
    Optional<ScreeningEntity> findByScreeningTime(String screeningTime);

}