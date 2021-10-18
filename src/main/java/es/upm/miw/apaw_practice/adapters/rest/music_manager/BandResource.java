package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.domain.models.music_manager.Band;
import es.upm.miw.apaw_practice.domain.models.music_manager.BandActiveUpdating;
import es.upm.miw.apaw_practice.domain.services.music_manager.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BandResource.BANDS)
public class BandResource {
    static final String BANDS = "/music_manager/bands";

    BandService bandService;

    @Autowired
    public BandResource(BandService bandService) {
        this.bandService = bandService;
    }

    @PostMapping()
    public Band create(@RequestBody Band band) {
        return this.bandService.create(band);
    }

    @PatchMapping
    public void updateActives(@RequestBody List<BandActiveUpdating> bandActiveUpdatingList) {
        this.bandService.updateActives(bandActiveUpdatingList.stream());
    }
}