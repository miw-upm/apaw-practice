package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.services.car_hire.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RenterResource.RENTER)
public class RenterResource {
    static final String RENTER = "/car-hire/renters";

    static final String DNI = "/{dni}";

    private final RenterService renterService;

    @Autowired
    public RenterResource(RenterService renterService) {
        this.renterService = renterService;
    }

    @PostMapping
    public Renter create(@RequestBody Renter renter) {
        return this.renterService.create(renter);
    }

    @PatchMapping(RenterResource.DNI)
    public Renter updateLikedCar(@PathVariable String dni, @RequestBody Renter renter) {
        return this.renterService.updateLikedCar(dni, renter);
    }
}
