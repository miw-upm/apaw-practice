package es.upm.miw.apaw.adapters.resources.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Position;
import es.upm.miw.apaw.domain.models.recruiting.PositionNumVacanciesUpdating;
import es.upm.miw.apaw.domain.services.recruiting.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PositionResource.POSITIONS)
public class PositionResource {

    public static final String POSITIONS = "/recruiting/positions";
    private final PositionService positionService;

    @Autowired
    public PositionResource(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public Position create(@Valid @RequestBody Position position) {
        return this.positionService.create(position);
    }

    @PatchMapping
    public void updateNumVacancies(List<PositionNumVacanciesUpdating> positionNumVacanciesUpdating) {
        this.positionService.updateNumVacancies(positionNumVacanciesUpdating.stream());
    }
}