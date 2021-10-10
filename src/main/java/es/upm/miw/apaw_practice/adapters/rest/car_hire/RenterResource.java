package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import es.upm.miw.apaw_practice.domain.services.car_hire.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RenterResource.RENTER)
public class RenterResource {

    static final String RENTER ="/car-hire/renters";

    private final RenterService renterService;

    @Autowired
    public RenterResource(RenterService renterService) {
        this.renterService = renterService;
    }

    @PostMapping
    public Renter create(@RequestBody Renter renter) {
        return this.renterService.create(renter);
    }

}
