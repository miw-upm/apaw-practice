package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.services.gun_store.GunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GunResource.GUNS)
public class GunResource {
    static final String GUNS = "/gun_store/guns";
    static final String NAME = "/{name}";

    private final GunService gunService;

    @Autowired
    public GunResource(GunService gunService) {
        this.gunService = gunService;
    }

    @GetMapping(NAME)
    public Gun getGunByName(@PathVariable String name) {
        return gunService.read(name);
    }
}
