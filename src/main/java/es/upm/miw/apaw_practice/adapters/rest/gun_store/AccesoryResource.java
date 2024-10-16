package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.AccesoryPriceUpdating;
import es.upm.miw.apaw_practice.domain.services.gun_store.AccesoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(AccesoryResource.ACCESORIES)
public class AccesoryResource {
    static final String ACCESORIES = "/gun_store/accesories";

    private final AccesoryService accesoryService;

    @Autowired
    public AccesoryResource(AccesoryService accesoryService) {
        this.accesoryService = accesoryService;
    }

    @PatchMapping
    public void updatePrice(@RequestBody AccesoryPriceUpdating accesoryPriceUpdating) {
        this.accesoryService.updatePrice(accesoryPriceUpdating);
    }
}
