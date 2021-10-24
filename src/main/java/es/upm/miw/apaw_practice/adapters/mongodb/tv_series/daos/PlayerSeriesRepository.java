package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.PlayerSeriesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerSeriesRepository extends MongoRepository<PlayerSeriesEntity,String> {

}
