package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.WushuSportSeederService;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class WushuSchoolPersistenceMongodbIT {

    @Autowired
    private WushuSchoolPersistenceMongodb wushuSchoolPersistenceMongodb;

    @Autowired
    private WushuSportSeederService wushuSportSeederService;


    @Test
    void testUpdate() {
        Optional<WushuSchool> wushuSchool = this.wushuSchoolPersistenceMongodb.readAll()
                .filter(school -> "Wushu Fuenlabrada".equals(school.getName()))
                .findFirst();
        assertTrue(wushuSchool.isPresent());
        List<Competitor> competitors = new ArrayList<>(wushuSchool.get().getCompetitors());
        competitors.clear();
        WushuGrade wushuGrade= new WushuGrade(LocalDate.now().minusYears(5), "Jin Shi", 1);
        List<CompetitionForm> competitionForms = new ArrayList<>();
        competitionForms.add(new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(30), "changquan"));
        competitors.add(new Competitor("WU/A/00126", 1, LocalDate.now(), wushuGrade, competitionForms));
        wushuSchool.get().setCompetitors(competitors);
        WushuSchool w = this.wushuSchoolPersistenceMongodb.update(wushuSchool.get());
        Optional<WushuSchool> newWushuSchool = this.wushuSchoolPersistenceMongodb.readAll()
                .filter(school ->  "Wushu Fuenlabrada".equals(school.getName()))
                .findFirst();
        assertTrue(newWushuSchool.isPresent());
        assertEquals(wushuSchool.get().getName(), newWushuSchool.get().getName());
        assertEquals(1, newWushuSchool.get().getCompetitors().size());
        assertEquals("Fuenlabrada", newWushuSchool.get().getLocation());
        wushuSportSeederService.deleteAll();
        wushuSportSeederService.seedDatabase();
    }

}
