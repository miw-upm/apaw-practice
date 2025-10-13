package es.upm.miw.apaw.adapters.resources.fighters;

import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import es.upm.miw.apaw.domain.services.fighters.MartialArtService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MartialArtResource.MARTIAL_ARTS)
public class MartialArtResource {
    public static final String MARTIAL_ARTS = "/fighters/martial-arts";
    public static final String DISCIPLINE = "/{discipline}";

    private final MartialArtService martialArtService;

    public MartialArtResource(MartialArtService martialArtService) {
        this.martialArtService = martialArtService;
    }

    @PutMapping(DISCIPLINE)
    public MartialArt update(@Valid @PathVariable String discipline, @RequestBody MartialArt martialArt){
        return this.martialArtService.update(discipline, martialArt);
    }

}
