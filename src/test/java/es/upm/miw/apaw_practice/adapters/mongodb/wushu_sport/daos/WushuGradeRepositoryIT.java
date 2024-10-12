package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class WushuGradeRepositoryIT {

    @Autowired
    private WushuGradeRepository wushuGradeRepository;
}
