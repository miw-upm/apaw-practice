package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import es.upm.miw.apaw_practice.domain.services.wushu_sport.CompetitionFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping(CompetitionFormResource.COMPETITION_FORM)
public class CompetitionFormResource {
    static final String COMPETITION_FORM = "/wushu/competititon-form";

    static final String SCHOOL_NAME_ID = "/{name}";
    static final String DURATION ="/duration";


    private final CompetitionFormService competitionFormService;

    @Autowired
    public CompetitionFormResource(CompetitionFormService competitionFormService) {
        this.competitionFormService = competitionFormService;
    }

    @PostMapping
    public CompetitionForm createCompetitionForm(@RequestBody CompetitionForm competitionForm) {
        return this.competitionFormService.create(competitionForm);
    }

    @GetMapping(DURATION+ SCHOOL_NAME_ID)
    public Duration getTotalDurationBySchoolName(@PathVariable String name) {
        return this.competitionFormService.getTotalDurationBySchoolName(name);
    }

}
