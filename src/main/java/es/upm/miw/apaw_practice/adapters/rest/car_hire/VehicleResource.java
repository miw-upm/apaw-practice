package es.upm.miw.apaw_practice.adapters.rest.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import es.upm.miw.apaw_practice.domain.services.car_hire.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VehicleResource.VEHICLES)
public class VehicleResource {
    static final String VEHICLES = "/car-hire/vehicles";

    static final String VIN_NUMBER = "/{vinNumber}";

    private final VehicleService vehicleService;

    @Autowired
    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PutMapping(VehicleResource.VIN_NUMBER)
    public Vehicle update(@PathVariable String vinNumber, @RequestBody Vehicle vehicle) {
        return this.vehicleService.updateVehicle(vinNumber, vehicle);
    }
}
