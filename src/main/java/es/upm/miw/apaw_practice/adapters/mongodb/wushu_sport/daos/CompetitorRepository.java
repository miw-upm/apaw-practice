package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetitorRepository extends MongoRepository<CompetitorEntity, String>  {
}
