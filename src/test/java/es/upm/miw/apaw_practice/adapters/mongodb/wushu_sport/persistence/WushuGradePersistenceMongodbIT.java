package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuGradeEntity;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class WushuGradePersistenceMongodbIT {

    @Autowired
    private WushuGradePersistenceMongodb wushuGradePersistenceMongodb;

    @Test
    void readByGradeTitle() {
        WushuGrade wushuGrade = this.wushuGradePersistenceMongodb.readByGradeTitle("Jin Shi");
        assertEquals("Jin Shi", wushuGrade.getGradeTitle());
        assertEquals(1, wushuGrade.getGradeLevel());
        assertEquals(LocalDate.now().minusYears(5), wushuGrade.getDateAwarded());
    }
    @Test
    void testUpdate() {
        WushuGrade wushuGrade= new WushuGrade(LocalDate.now().minusYears(5), "Jin Shi", 3);
        this.wushuGradePersistenceMongodb.update("Jin Shi", wushuGrade);
        WushuGrade wushuGradeBD = this.wushuGradePersistenceMongodb.readByGradeTitle("Jin Shi");
        assertEquals(3, wushuGradeBD.getGradeLevel());
    }
}
