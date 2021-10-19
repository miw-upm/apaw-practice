package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.services.game_wow.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(FeatureResource.GAMEWOW_FEATURES)
public class FeatureResource {

    static final String GAMEWOW_FEATURES = "/gamewow/features";
    static final String SEARCH = "/search";
    private final FeatureService featureService;

    @Autowired
    public FeatureResource(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping
    public Feature create(@RequestBody Feature feature) {
        return this.featureService.create(feature);
    }

    @PatchMapping
    public void updateTemples(@RequestBody Integer temple) {
        this.featureService.updateTemple(temple);
    }

    // GET */drops/search?q=boss-effort:value
    @GetMapping(SEARCH)
    public List<String> findByDescriptionBoss(@RequestParam String q){
        String descriptionBoss = new LexicalAnalyzer().extractWithAssure(q, "descriptionBoss", String::new);
        return  featureService.findByDescriptionBoss(descriptionBoss);
    }
}
