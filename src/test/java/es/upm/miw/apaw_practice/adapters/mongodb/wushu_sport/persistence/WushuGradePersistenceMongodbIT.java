package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuGradeEntity;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class WushuGradePersistenceMongodbIT {

    @Autowired
    private WushuGradePersistenceMongodb wushuGradePersistenceMongodb;

    @Test
    void readByGradeTitle() {
        WushuGrade wushuGrade = this.wushuGradePersistenceMongodb.readByGradeTitle("Gold Shi");
        assertEquals("Gold Shi", wushuGrade.getGradeTitle());
        assertEquals(4, wushuGrade.getGradeLevel());
        assertEquals(LocalDate.now().minusYears(2), wushuGrade.getDateAwarded());
    }
    @Test
    void testUpdate() {
        WushuGrade wushuGrade= new WushuGrade(LocalDate.now().minusYears(1), "Diamond Shi", 3);
        this.wushuGradePersistenceMongodb.update("Diamond Shi", wushuGrade);
        WushuGrade wushuGradeBD = this.wushuGradePersistenceMongodb.readByGradeTitle("Diamond Shi");
        assertEquals(3, wushuGradeBD.getGradeLevel());
    }
}
