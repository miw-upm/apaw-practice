package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuSchoolEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WushuSchoolRepository extends MongoRepository<WushuSchoolEntity, String> {
    Optional<WushuSchoolEntity> findByName(String licence);

}
