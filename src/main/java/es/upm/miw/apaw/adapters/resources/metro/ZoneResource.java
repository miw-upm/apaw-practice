package es.upm.miw.apaw.adapters.resources.metro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import es.upm.miw.apaw.domain.models.metro.Zone;
import es.upm.miw.apaw.domain.services.metro.ZoneService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ZoneResource.ZONES)
public class ZoneResource {
    public static final String ZONES = "/metro/zones";
    public static final String TYPE = "/{type}";
    private final ZoneService zoneService;

    @Autowired
    public ZoneResource(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PutMapping(TYPE)
    public Zone update(@Valid @PathVariable String type, @Valid @RequestBody Zone zone) {
        return this.zoneService.update(type, zone);
    }
}