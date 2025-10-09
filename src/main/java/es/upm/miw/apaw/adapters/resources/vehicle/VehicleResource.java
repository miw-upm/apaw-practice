package es.upm.miw.apaw.adapters.resources.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.services.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(VehicleResource.VEHICLES)
public class VehicleResource {
    public static final String VEHICLES = "/vehicle/vehicles";
    public static final String SEARCH = "/searches";
    public static final String EXTRA_CATEGORIES = "/extra/categories";
    public static final String USER_MOBILES = "/user/mobiles";

    private final VehicleService vehicleService;

    @Autowired
    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public Stream<Vehicle> findByBrand(@RequestParam String brand) {
        return this.vehicleService.findByBrand(brand);
    }

    // Search 1 -> issue#1250
    @GetMapping(SEARCH + EXTRA_CATEGORIES)
    public List<String> findExtraCategoriesByDocumentationName(@RequestParam String documentationName) {
        return this.vehicleService.findExtraCategoriesByDocumentationName(documentationName);
    }

    // Search 2 -> issue#1251
    @GetMapping(SEARCH + USER_MOBILES)
    public List<String> findUserMobilesByEngineType(@RequestParam String engineType) {
        return this.vehicleService.findUserMobilesByEngineType(engineType);
    }
}
