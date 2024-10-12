package es.upm.miw.apaw_practice.adapters.rest.car;


import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import es.upm.miw.apaw_practice.domain.services.car.OwnerCarService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping(OwnerCarResource.OWNER)
public class OwnerCarResource {

    static final String OWNER = "/car/owner";

    static final String DRIVERLICENSE = "/{driverLicense}";

    static final String SEARCH = "/search";

    private OwnerCarService ownerService;

    public OwnerCarResource(OwnerCarService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(DRIVERLICENSE)
    public OwnerCar read(@PathVariable String driverLicense) {
        return ownerService.read(driverLicense);
    }

    @PatchMapping(DRIVERLICENSE)
    public OwnerCar updateName(@PathVariable String driverLicense, @RequestBody String name) {
        return this.ownerService.updateName(driverLicense,name);
    }

    @GetMapping(SEARCH)
    public BigDecimal getTotalCostByDriverLicense(@RequestParam Optional<String> q){
        if (q.isEmpty()) {
            throw new BadRequestException("q parameter expected but not sent.");
        }
        String driverLicense = new LexicalAnalyzer().extractWithAssure(q.get(), "driverLicense");
        return this.ownerService.getTotalCostByDriverLicense(driverLicense);
    }
}
