package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.services.game_wow.DropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(DropResource.GAMEWOW_DROPS)
public class DropResource {
    static final String GAMEWOW_DROPS = "/gamewow/drops";
    static final String SEARCH = "/search";

    private final DropService dropService;

    @Autowired
    public DropResource(DropService dropService) {
        this.dropService = dropService;
    }

    // GET */drops/search?q=boss-effort:value
    @GetMapping(SEARCH)
    public Stream<Drop> findByEffort(@RequestParam String q){
        String effort = new LexicalAnalyzer().extractWithAssure(q, "effort", String::new);
        return  dropService.findByEffort(effort)
                .flatMap(boss -> boss.getDropList().stream());
    }
}
