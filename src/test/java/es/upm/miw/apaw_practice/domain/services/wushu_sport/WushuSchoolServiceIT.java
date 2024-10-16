package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.WushuSportSeederService;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class WushuSchoolServiceIT {

    @Autowired
    private WushuSchoolService wushuSchoolService;

    @Autowired
    private WushuSportSeederService wushuSportSeederService;

    @Test
    void testUpdateWushuSchool() {
        CompetitionForm competitionForm = new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(30), "changquan");
        WushuGrade wushuGrade = new WushuGrade(LocalDate.now().minusYears(5), "Jin Shi", 1);
        Competitor competitor = new Competitor("WU/A/00128", 3, LocalDate.now().minusYears(2), wushuGrade, List.of(competitionForm));
        List<Competitor> newCompetitorList = List.of(competitor);
        WushuSchool wushuSchool = this.wushuSchoolService.updateWushuSchool("Wushu Fuenlabrada", newCompetitorList);
        assertEquals(1, wushuSchool.getCompetitors().size());
        assertEquals("WU/A/00128", wushuSchool.getCompetitors().get(0).getLicence());
        this.wushuSportSeederService.deleteAll();
        this.wushuSportSeederService.seedDatabase();
    }
}
