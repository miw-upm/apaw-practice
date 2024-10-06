package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.CompetitionFormRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.CompetitorRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.WushuGradeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.daos.WushuSchoolRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitionFormEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.CompetitorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WuhsuGradeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities.WushuSchoolEntity;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class WushuSportSeederService {

    @Autowired
    private CompetitionFormRepository competitionFormRepository;
    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private WushuGradeRepository wushuGradeRepository;
    @Autowired
    private WushuSchoolRepository wushuSchoolRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- WushuSport Initial Load -----------");
        CompetitionFormEntity[] competitionForms = {
                new CompetitionFormEntity(new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(30), "changquan")),
                new CompetitionFormEntity(new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(16), "nanquan")),
                new CompetitionFormEntity(new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(13), "gunshu")),
                new CompetitionFormEntity(new CompetitionForm(9.45, Duration.ofMinutes(1).plusSeconds(40), "daoshu")),
                new CompetitionFormEntity(new CompetitionForm(9.45, Duration.ofMinutes(2), "nandao")),
        };
        this.competitionFormRepository.saveAll(Arrays.asList(competitionForms));

        WuhsuGradeEntity[] wushuGrades = {
                new WuhsuGradeEntity(new WushuGrade(LocalDate.now().minusYears(5), "Jin Shi", 1)),
                new WuhsuGradeEntity(new WushuGrade(LocalDate.now().minusYears(4), "Yin Shi", 2)),
                new WuhsuGradeEntity(new WushuGrade(LocalDate.now().minusYears(3), "Jade Shi", 3)),
                new WuhsuGradeEntity(new WushuGrade(LocalDate.now().minusYears(2), "Gold Shi", 4)),
                new WuhsuGradeEntity(new WushuGrade(LocalDate.now().minusYears(1), "Diamond Shi", 5))
        };
        this.wushuGradeRepository.saveAll(Arrays.asList(wushuGrades));

        CompetitorEntity[] competitors = {
                new CompetitorEntity("WU/A/00126", 1, LocalDate.now(), wushuGrades[0], List.of(competitionForms[0])),
                new CompetitorEntity("WU/A/00127", 2, LocalDate.now().minusYears(1), wushuGrades[0], List.of(competitionForms[1],competitionForms[2])),
                new CompetitorEntity("WU/A/00128", 3, LocalDate.now().minusYears(2), wushuGrades[0], List.of(competitionForms[0], competitionForms[1])),
                new CompetitorEntity("WU/A/00129", 4, LocalDate.now().minusYears(3), wushuGrades[0], List.of(competitionForms[2])),
        };
        this.competitorRepository.saveAll(Arrays.asList(competitors));

        WushuSchoolEntity[] wushuSchools = {
                new WushuSchoolEntity("Fuenlabrada", "Wushu Fuenlabrada", true, List.of(competitors[0], competitors[1])),
                new WushuSchoolEntity("Albacete", "Wushu Daxue", true, List.of(competitors[2])),
                new WushuSchoolEntity("Madrid", "Jingwu", false, List.of(competitors[3])),
        };
        this.wushuSchoolRepository.saveAll(Arrays.asList(wushuSchools));
    }

    public void deleteAll() {
        this.competitionFormRepository.deleteAll();
        this.competitorRepository.deleteAll();
        this.wushuGradeRepository.deleteAll();
        this.wushuSchoolRepository.deleteAll();
    }

}
