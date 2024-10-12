package es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.springframework.stereotype.Repository;

@Repository
public interface WushuGradePersistence {

    void delete(String gradeTitle);

    WushuGrade update(String gradeTitle, WushuGrade grade);

    WushuGrade readByGradeTitle(String gradeTitle);

}
