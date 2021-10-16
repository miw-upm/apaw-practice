package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.services.gym.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AthleteResource.athlets)
public class AthleteResource {
    static final String athlets = "/gym/athlet";
    private final AthleteService athletService;

    @Autowired
    public AthleteResource(AthleteService athletService) {
        this.athletService = athletService;
    }

    @PostMapping
    public Athlete creat(@RequestBody Athlete athlete) {
        return this.athletService.create(athlete);
    }
}
