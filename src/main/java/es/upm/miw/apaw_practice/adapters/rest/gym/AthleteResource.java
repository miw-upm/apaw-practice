package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.AthleteNameUpdating;
import es.upm.miw.apaw_practice.domain.services.gym.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AthleteResource.ATHLETE)
public class AthleteResource {
    static final String ATHLETE = "/gym/athlete";
    static final String NAME = "/name";

    private final AthleteService athleteService;

    @Autowired
    public AthleteResource(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @PostMapping
    public Athlete creat(@RequestBody Athlete athlete) {
        return this.athleteService.create(athlete);
    }


    @PatchMapping(NAME)
    public void updateAthleteName(@RequestBody AthleteNameUpdating athleteNameUpdating) {
        if (athleteNameUpdating.getOldName() == null || athleteNameUpdating.getNewName() == null) {
            throw new BadRequestException("Athlete Name :" + athleteNameUpdating);
        } else {
            this.athleteService.updateAthleteName(athleteNameUpdating);
        }
    }
}
