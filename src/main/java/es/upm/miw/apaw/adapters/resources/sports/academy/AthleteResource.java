package es.upm.miw.apaw.adapters.resources.sports.academy;

import es.upm.miw.apaw.domain.models.sports.academy.Athlete;
import es.upm.miw.apaw.domain.models.sports.academy.enums.RelationShip;
import es.upm.miw.apaw.domain.services.sports.academy.AthleteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AthleteResource.ATHLETES)
@Tag(name = "Sports Academy", description = "Operations related to Sports Academy Entities")
public class AthleteResource {
    public static final String ATHLETES = "/sports-academy/athletes";
    public static final String ID_ID = "/{id}";
    public static final String SPORT_MODALITY_PROFESSOR_SPECIALIZATIONS = "/sport-modalities/professors/specializations";
    public static final String HEIGHT_BY_LEGAL_GUARDIAN = "/average-height";
    private final AthleteService athleteService;

    @Autowired
    public AthleteResource(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping(ID_ID)
    public Athlete getById(@Valid @PathVariable UUID id) {
        return this.athleteService.getById(id);
    }

    @GetMapping(SPORT_MODALITY_PROFESSOR_SPECIALIZATIONS)
    public List<String> getUniqueProfessorSpecializations(@Valid @RequestParam String legalGuardianSecondMobile){
        return athleteService.getUniqueProfessorSpecializations(legalGuardianSecondMobile);
    }

    @GetMapping(HEIGHT_BY_LEGAL_GUARDIAN)
    public double getAverageHeightByLegalGuardian(@Valid @RequestParam RelationShip legalGuardianRelationShip){
        return athleteService.getAverageHeightByLegalGuardian(legalGuardianRelationShip);
    }
}
