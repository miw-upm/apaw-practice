package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class WushuGradePersistenceMongodbIT {

    @Autowired
    private WushuGradePersistenceMongodb wushuGradePersistenceMongodb;

    @Test
    void readByGradeTitle() {
        WushuGrade wushuGrade = this.wushuGradePersistenceMongodb.readByGradeTitle("Diamond Shi");
        assertEquals("Diamond Shi", wushuGrade.getGradeTitle());
        assertEquals(5, wushuGrade.getGradeLevel());
        assertEquals(LocalDate.now().minusYears(1), wushuGrade.getDateAwarded());
    }
    @Test
    void testUpdate() {
        WushuGrade wushuGrade= new WushuGrade(LocalDate.now().minusYears(1), "Diamond Shi", 3);
        this.wushuGradePersistenceMongodb.update("Diamond Shi", wushuGrade);
        WushuGrade wushuGradeBD = this.wushuGradePersistenceMongodb.readByGradeTitle("Diamond Shi");
        assertEquals(3, wushuGradeBD.getGradeLevel());
    }
}
