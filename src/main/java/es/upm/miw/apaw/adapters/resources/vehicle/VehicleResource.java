package es.upm.miw.apaw.adapters.resources.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.services.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(VehicleResource.VEHICLES)
public class VehicleResource {
    public static final String VEHICLES = "/vehicle/vehicles";

    private final VehicleService vehicleService;

    @Autowired
    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public Stream<Vehicle> findByBrand(@RequestParam String brand) {
        return this.vehicleService.findByBrand(brand);
    }
}
