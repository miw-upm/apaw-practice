package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.AwardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class AwardRepositoryIT {

    @Autowired
    private AwardRepository awardRepository;

    @Test
    void testFindByNameCategoryYearNotExists() {
        assertTrue(awardRepository.findByNameCategoryYear("Oscar_1994_BestActor").isEmpty());
    }

    @Test
    void testFindByNameCategoryYear() {
        assertTrue(this.awardRepository.findByNameCategoryYear("Oscar_1981_BestActor").isPresent());
        AwardEntity award = this.awardRepository.findByNameCategoryYear("Oscar_1981_BestActor").get();
        assertEquals("Oscar", award.getName());
        assertEquals(LocalDate.of(1981, 3, 31), award.getYear());
    }

}
