package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class CompetitorRepositoryIT {

    @Autowired
    private CompetitorRepository competitorRepository;

    @Test
    void testFindByLicence() {
        assertTrue(this.competitorRepository.findByLicence("WU/A/00126").isPresent());
        CompetitorEntity competitorEntity = this.competitorRepository.findByLicence("WU/A/00126").get();
        assertEquals(competitorEntity.getFederatedYears(), 1);
        assertEquals(competitorEntity.getLastFederationDate(), LocalDate.now());
        assertEquals(competitorEntity.getWuhsuGradeEntity().getGradeTitle(), "Jin Shi");
        assertEquals(competitorEntity.getCompetitionFormsEntities().size(), 1);
    }

}
