package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class CompetitorServiceIT {

    @Autowired
    private CompetitorService competitorService;

    @Test
    void testReadByLicence(){
        Competitor competitor = this.competitorService.readByLicence("WU/A/00126");
        assertEquals(competitor.getLicence(), "WU/A/00126");
        assertEquals(competitor.getCompetitionForms().size(), 1);
        assertEquals(competitor.getFederatedYears(), 1);
        assertEquals(competitor.getWushuGrade().getGradeTitle(),"Jin Shi");
        assertEquals( LocalDate.now(), competitor.getLastFederationDate());
    }
}
