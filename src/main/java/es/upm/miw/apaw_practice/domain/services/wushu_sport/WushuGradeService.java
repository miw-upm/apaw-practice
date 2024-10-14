package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.WushuGrade;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.CompetitionFormPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.CompetitorPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.WushuGradePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WushuGradeService {

    private final WushuGradePersistence wushuGradePersistence;

    private final CompetitionFormPersistence competitionFormPersistence;

    private final CompetitorPersistence competitorPersistence;

    @Autowired
    public WushuGradeService(WushuGradePersistence wushuGradePersistence, CompetitionFormPersistence competitionFormPersistence, CompetitorPersistence competitorPersistence) {
        this.wushuGradePersistence = wushuGradePersistence;
        this.competitionFormPersistence = competitionFormPersistence;
        this.competitorPersistence = competitorPersistence;
    }

    public void delete(String gradeTitle){
        this.wushuGradePersistence.delete(gradeTitle);
    }

    public void updateGradeLevel(String gradeTitle, Integer level){
        WushuGrade wushuGrade = this.wushuGradePersistence.readByGradeTitle(gradeTitle);
        wushuGrade.setGradeLevel(level);
        this.wushuGradePersistence.update(wushuGrade.getGradeTitle(),wushuGrade);
    }

    public List<String> getGradeTitleListByCategory(String category){
        List<CompetitionForm> competitionForms = this.competitionFormPersistence.readAll()
                .filter(form -> category.equals(form.getCategory())).toList();
        List<Competitor> competitors = this.competitorPersistence.readAll()
                .filter(competitor -> competitor.getCompetitionForms().stream()
                        .anyMatch(competitionForms::contains)).toList();

        return  competitors.stream()
                .map(Competitor::getWushuGrade)
                .map(WushuGrade::getGradeTitle)
                .distinct()
                .toList();
    }
}
