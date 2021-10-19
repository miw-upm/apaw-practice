package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.AthleteNameUpdating;
import es.upm.miw.apaw_practice.domain.services.gym.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AthleteResource.athlets)
public class AthleteResource {
    static final String athlets = "/gym/athlet";
    static final String Name = "/name";

    private final AthleteService athletService;

    @Autowired
    public AthleteResource(AthleteService athletService) {
        this.athletService = athletService;
    }

    @PostMapping
    public Athlete creat(@RequestBody Athlete athlete) {
        return this.athletService.create(athlete);
    }


    @PatchMapping(Name)
    public void updateAtheleName(@RequestBody AthleteNameUpdating athleteNameUpdating) {
        if (athleteNameUpdating.getOldName() == null || athleteNameUpdating.getNewName() == null) {
            throw new BadRequestException("");
        } else {
            this.athletService.updateAtheleteName(athleteNameUpdating);
        }
    }
}
