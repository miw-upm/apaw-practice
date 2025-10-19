package es.upm.miw.apaw.adapters.resources.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.dtos.UpdateSportMobilityActivation;
import es.upm.miw.apaw.domain.services.sports.academy.SportModalityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SportModalityResource.SPORT_MODALITIES)
@Tag(name = "Sports Academy", description = "Operations related to Sports Academy Entities")
public class SportModalityResource {
    public static final String SPORT_MODALITIES = "/sports-academy/sport-modalities";
    public static final String ID_ID = "/{id}";

    private final SportModalityService sportModalityService;

    @Autowired
    public SportModalityResource(SportModalityService sportModalityService) {
        this.sportModalityService = sportModalityService;
    }

    @PatchMapping(ID_ID)
    public void addAthlete(@Valid @PathVariable UUID id, @RequestBody UpdateSportMobilityActivation updateSportMobilityActivation) {
        this.sportModalityService.updateActivation(id, updateSportMobilityActivation);
    }

    @DeleteMapping(ID_ID)
    public void delete(@Valid @PathVariable UUID id) {
        this.sportModalityService.delete(id);
    }
}
