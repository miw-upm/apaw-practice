package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuGradeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class WushuGradeRepositoryIT {

    @Autowired
    private WushuGradeRepository wushuGradeRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.wushuGradeRepository.findByGradeTitle("Jin Shi").isPresent());
        WushuGradeEntity wushuGrade = this.wushuGradeRepository.findByGradeTitle("Jin Shi").get();
        assertEquals("Jin Shi", wushuGrade.getGradeTitle());
        assertEquals(1, wushuGrade.getGradeLevel());
        assertEquals(LocalDate.now().minusYears(5), wushuGrade.getDateAwarded());

    }
}
