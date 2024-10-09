package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.services.wushu_sport.CompetitionFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CompetitionFormResource.COMPETITION_FORM)
public class CompetitionFormResource {
    static final String COMPETITION_FORM = "/wushu/competititon-form";

    private final CompetitionFormService competitionFormService;

    @Autowired
    public CompetitionFormResource(CompetitionFormService competitionFormService) {
        this.competitionFormService = competitionFormService;
    }

    @PostMapping
    public CompetitionForm createCompetitionForm(@RequestBody CompetitionForm competitionForm) {
        return this.competitionFormService.create(competitionForm);
    }

}
