package es.upm.miw.apaw_practice.domain.services.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.persistence_ports.wushu_sport.CompetitionFormPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionFormService {

    private final CompetitionFormPersistence competitionFormPersistence;

    @Autowired
    public CompetitionFormService(CompetitionFormPersistence competitionFormPersistence) {
        this.competitionFormPersistence = competitionFormPersistence;
    }

    public CompetitionForm create(CompetitionForm competitionForm) {
        return this.competitionFormPersistence.create(competitionForm);
    }

}
