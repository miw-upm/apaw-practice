package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompetitorRepository extends MongoRepository<CompetitorEntity, String>  {
    Optional<CompetitorEntity> findByLicence(String licence);
}
