package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.domain.models.military.Unit;
import es.upm.miw.apaw_practice.domain.services.military.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UnitResource.UNITS)
public class UnitResource {
    static final String UNITS = "/military/units";

    private final UnitService unitService;

    @Autowired
    public UnitResource(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    public Unit create(@RequestBody Unit unit) {
        return this.unitService.create(unit);
    }
}
