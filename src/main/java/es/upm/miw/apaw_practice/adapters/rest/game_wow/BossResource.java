package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.domain.services.game_wow.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(BossResource.GAMEWOW_BOSSES)
public class BossResource {

    static final String GAMEWOW_BOSSES = "/gamewow/bosses";
    static final String NAME_DESCRIPTION = "/{description}";
    static final String EFFORT = "/{effort}";

    private BossService bossService;

    @Autowired
    public BossResource(BossService bossService) {
        this.bossService = bossService;
    }

    @DeleteMapping(NAME_DESCRIPTION + EFFORT)
    public void delete(@PathVariable String description, @PathVariable String effort) {
        this.bossService.delete(description,effort);
    }

}
