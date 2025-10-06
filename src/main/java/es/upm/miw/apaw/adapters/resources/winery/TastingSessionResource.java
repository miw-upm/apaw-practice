package es.upm.miw.apaw.adapters.resources.winery;

import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.services.winery.TastingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(TastingSessionResource.TASTING_SESSIONS)
public class TastingSessionResource {

    public static final String TASTING_SESSIONS = "/winery/tasting-sessions";

    public static final String ID = "/{id}";

    private final TastingSessionService tastingSessionService;

    @Autowired
    public TastingSessionResource(TastingSessionService tastingSessionService) {
        this.tastingSessionService = tastingSessionService;
    }

    @GetMapping(ID)
    public TastingSession read(@PathVariable UUID id) {
        return this.tastingSessionService.read(id);
    }
}

