package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitionFormEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompetitionFormRepository  extends MongoRepository<CompetitionFormEntity, String> {
}
