package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.EpisodeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EpisodeRepository extends MongoRepository<EpisodeEntity,String> {
}
