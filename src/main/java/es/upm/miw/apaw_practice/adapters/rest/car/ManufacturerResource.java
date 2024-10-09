package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.services.car.ManufacturerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ManufacturerResource.MANUFACTURER)
public class ManufacturerResource {

    static final String MANUFACTURER = "/manufacturer";

    static final String NAME_ID = "/{name}";

    private ManufacturerService manufacturerService;

    public ManufacturerResource(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PutMapping(NAME_ID)
    public Manufacturer update(@PathVariable String name, @RequestBody Manufacturer manufacturer){
        if (!manufacturerService.existsName(name)) {
            throw new BadRequestException("No manufacturer found with name " + name);
        }
        return this.manufacturerService.update(name, manufacturer);
    }
}