package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.domain.models.military.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UnitResource.UNITS)
public class UnitResource {
    static final String UNITS = "/military/units";

    @Autowired
    public UnitResource() {
        // empty for now
    }

    @PostMapping
    public Unit create(@RequestBody Unit unit) {
        return new Unit(unit.getName(), unit.getBranch(), unit.getLocation(), unit.getSoldiers());
    }
}
