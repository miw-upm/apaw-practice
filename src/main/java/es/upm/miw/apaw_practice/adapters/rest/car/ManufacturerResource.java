package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.services.car.ManufacturerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ManufacturerResource.MANUFACTURER)
public class ManufacturerResource {

    static final String MANUFACTURER = "/manufacturer";

    static final String NAME_ID = "/{name}";

    static final String SEARCH = "/search";

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

    @GetMapping(SEARCH)
    public List<String> findOwnerNamesByManufacturerCountry(@RequestParam Optional<String> q){
        if (q.isEmpty()) {
            throw new BadRequestException("q parameter expected but not sent.");
        }
        String country = new LexicalAnalyzer().extractWithAssure(q.get(), "country");
        return this.manufacturerService.findOwnerNamesByManufacturerCountry(country);
    }
}
