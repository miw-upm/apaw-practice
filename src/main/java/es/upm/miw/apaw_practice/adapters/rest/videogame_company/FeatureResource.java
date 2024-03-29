package es.upm.miw.apaw_practice.adapters.rest.videogame_company;

import es.upm.miw.apaw_practice.domain.models.videogame_company.Feature;
import es.upm.miw.apaw_practice.domain.services.videogame_company.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(FeatureResource.FEATURES)
public class FeatureResource {
    static final String FEATURES = "/videogame-company/features";
    static final String GENRE = "/{genre}";
    static final String CLOUD_SYNCHRONIZATION = "/cloud-synchronization";
    private final FeatureService featureService;

    @Autowired
    public FeatureResource(FeatureService featureService){
        this.featureService = featureService;
    }

    @PutMapping(GENRE+CLOUD_SYNCHRONIZATION)
    public Feature updateFeatureCloudSynchronization(@PathVariable String genre,
                                                     @RequestBody Boolean cloudSynchronization){
        return this.featureService.updateCloudSynchronization(genre, cloudSynchronization);
    }
}
