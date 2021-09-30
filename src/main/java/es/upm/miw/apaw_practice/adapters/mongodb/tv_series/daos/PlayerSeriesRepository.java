package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.PlayerSeriesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlayerSeriesRepository extends MongoRepository<PlayerSeriesEntity,String> {
    Optional<PlayerSeriesEntity> findByName(String name);
}
