package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.services.emarketer.CupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CupsResource.CUPS)
public class CupsResource {
    static final String CUPS = "/emarketer/cups";

    static final String CUP = "/{cup}";

    private final CupsService cupsService;

    @Autowired
    public CupsResource(CupsService cupsService) {
        this.cupsService = cupsService;
    }

    @PutMapping(CUP)
    public void updateEnergy(@PathVariable String cup, @RequestBody Cups cups) {
        this.cupsService.updateEnergy(cup, cups);
    }
}
