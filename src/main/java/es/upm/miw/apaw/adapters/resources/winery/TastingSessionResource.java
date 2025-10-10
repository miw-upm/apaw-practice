package es.upm.miw.apaw.adapters.resources.winery;

import es.upm.miw.apaw.domain.models.winery.Evaluation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.services.winery.TastingSessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(TastingSessionResource.TASTING_SESSIONS)
public class TastingSessionResource {

    public static final String TASTING_SESSIONS = "/winery/tasting-sessions";

    public static final String ID = "/{id}";
    public static final String EVALUATIONS = "/evaluations";

    private final TastingSessionService tastingSessionService;

    @Autowired
    public TastingSessionResource(TastingSessionService tastingSessionService) {
        this.tastingSessionService = tastingSessionService;
    }

    @GetMapping(ID)
    public TastingSession read(@PathVariable UUID id) {
        return this.tastingSessionService.read(id);
    }

    @PutMapping(ID + EVALUATIONS)
    public TastingSession updateEvaluations(@Valid @PathVariable UUID id, @RequestBody List<Evaluation> evaluationList) {
        return this.tastingSessionService.updateEvaluations(id, evaluationList);
    }
}

