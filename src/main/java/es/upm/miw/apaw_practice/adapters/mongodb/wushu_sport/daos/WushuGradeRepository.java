package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuGradeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WushuGradeRepository extends MongoRepository<WushuGradeEntity, String>  {

    Optional<WushuGradeEntity> findByGradeTitle(String gradeTitle);

    void deleteByGradeTitle(String name);

}
