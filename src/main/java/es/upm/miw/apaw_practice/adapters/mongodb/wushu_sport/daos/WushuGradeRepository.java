package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuGradeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WushuGradeRepository extends MongoRepository<WushuGradeEntity, String>  {

    void deleteByGradeTitle(String name);

}
