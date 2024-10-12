package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.WushuGradeRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.WushuGradePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("wushuGradePersistence")
public class WushuGradePersistenceMongodb implements WushuGradePersistence {

    private final WushuGradeRepository wushuGradeRepository;

    @Autowired
    public WushuGradePersistenceMongodb(WushuGradeRepository wushuGradeRepository){
        this.wushuGradeRepository = wushuGradeRepository;
    }

    @Override
    public void delete(String gradeTitle){
        this.wushuGradeRepository.deleteByGradeTitle(gradeTitle);
    }


}
