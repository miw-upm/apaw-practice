package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.services.military.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(SoldierResource.SOLDIERS)
public class SoldierResource {
    static final String SOLDIERS = "/military/soldiers";

    private final SoldierService soldierService;

    @Autowired
    public SoldierResource(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    @GetMapping
    public Stream<Soldier> findAll() {
        return this.soldierService.findAll();
    }
}
