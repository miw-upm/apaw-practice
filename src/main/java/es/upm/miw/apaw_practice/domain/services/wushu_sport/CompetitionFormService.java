package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuSchool;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.CompetitionFormPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.WushuSchoolPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class CompetitionFormService {

    private final CompetitionFormPersistence competitionFormPersistence;

    private final WushuSchoolPersistence wushuSchoolPersistence;

    @Autowired
    public CompetitionFormService(CompetitionFormPersistence competitionFormPersistence,  WushuSchoolPersistence wushuSchoolPersistence) {
        this.competitionFormPersistence = competitionFormPersistence;
        this.wushuSchoolPersistence = wushuSchoolPersistence;
    }

    public CompetitionForm create(CompetitionForm competitionForm) {
        return this.competitionFormPersistence.create(competitionForm);
    }

    public Duration getTotalDurationBySchoolName(String schoolName) {
        WushuSchool wushuSchool = this.wushuSchoolPersistence.readByName(schoolName);
        List<Competitor> competitorList = wushuSchool.getCompetitors();

        return competitorList.stream()
                .flatMap(competitor -> competitor.getCompetitionForms().stream())
                .map(CompetitionForm::getDuration)
                .reduce(Duration.ZERO, Duration::plus);
    }
}
