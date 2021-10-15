package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.services.game_wow.FeatureService;
import es.upm.miw.apaw_practice.domain.services.shop.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FeatureResource.GAMEWOW_FEATURES)
public class FeatureResource {

    static final String GAMEWOW_FEATURES = "/gamewow/features";
    private final FeatureService featureService;

    @Autowired
    public FeatureResource(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping
    public Feature create(@RequestBody Feature feature) {
        return this.featureService.create(feature);
    }
}
