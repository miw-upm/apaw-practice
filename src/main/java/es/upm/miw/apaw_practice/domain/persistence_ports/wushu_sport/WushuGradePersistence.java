package es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport;

import org.springframework.stereotype.Repository;

@Repository
public interface WushuGradePersistence {

    void delete(String gradeTitle);
}
