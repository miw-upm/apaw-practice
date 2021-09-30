package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.TvSeriesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TvSeriesRepository extends MongoRepository<TvSeriesEntity,String> {
    Optional<TvSeriesEntity> findByTitle(String title);
}
