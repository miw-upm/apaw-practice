package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.WushuGradePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WushuGradeService {

    private final WushuGradePersistence wushuGradePersistence;

    @Autowired
    public WushuGradeService(WushuGradePersistence wushuGradePersistence){
        this.wushuGradePersistence = wushuGradePersistence;
    }

    public void delete(String gradeTitle){
        this.wushuGradePersistence.delete(gradeTitle);
    }

}
