package es.upm.miw.apaw.adapters.resources.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.services.sports.academy.AthleteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(AthleteResource.ATHLETES)
public class AthleteResource {
    public static final String ATHLETES = "/sports-academy/athletes";
    public static final String ID_ID = "/{id}";
    private final AthleteService athleteService;

    @Autowired
    public AthleteResource(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping(ID_ID)
    public Athlete getById(@Valid @PathVariable UUID id) {
        return this.athleteService.getById(id);
    }
}
