package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.domain.models.competition.Competition;
import es.upm.miw.apaw_practice.domain.services.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CompetitionResource.COMPETITION)
public class CompetitionResource {
    static final String COMPETITION = "/competition";
    static final String ID_ID = "/{id}";

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionResource(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }


    @GetMapping(ID_ID)
    public Competition getCompetition(@PathVariable String id) {
        return this.competitionService.readById(id);
    }
}
