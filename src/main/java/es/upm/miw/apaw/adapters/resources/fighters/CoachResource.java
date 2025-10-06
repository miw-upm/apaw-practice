package es.upm.miw.apaw.adapters.resources.fighters;

import es.upm.miw.apaw.domain.models.fighters.Coach;
import es.upm.miw.apaw.domain.services.fighters.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CoachResource.COACHES)
public class CoachResource {

    public static final String COACHES = "/fighters/coaches";
    public static final String NAME_ID = "/{fullName}";

    private final CoachService coachService;

    @Autowired
    public CoachResource(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping(NAME_ID)
    public Coach readByFullName(@PathVariable String fullName) {
        return this.coachService.readByFullName(fullName);
    }
}