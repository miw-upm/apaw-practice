package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.WushuSportSeederService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class CompetitionFormServiceIT {

    @Autowired
    private CompetitionFormService competitionFormService;

    @Autowired
    private WushuSportSeederService wushuSportSeederService;


    @Test
    void testGetTotalDurationBySchoolName() {
        this.wushuSportSeederService.deleteAll();
        this.wushuSportSeederService.seedDatabase();
        String wushuSchoolName = "Wushu Fuenlabrada";
        Duration sumDuration = this.competitionFormService.getTotalDurationBySchoolName(wushuSchoolName);
        assertEquals(Duration.ofMinutes(3).plusSeconds(59), sumDuration);
    }

}
