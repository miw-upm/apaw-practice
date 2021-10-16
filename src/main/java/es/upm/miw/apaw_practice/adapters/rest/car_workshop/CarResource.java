package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.services.car_workshop.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(CarResource.CARS)
public class CarResource {
    static final String CARS = "/car-workshop/cars";
    static final String LICENSE_PLATE = "/{licensePlate}";
    static final String OWNERS_DNI = "/owners/{dni}";

    private final CarService carService;

    @Autowired
    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public void create(@RequestBody Car car) {
        if (Objects.isNull(car.getLicensePlate()) ||
                Objects.isNull(car.getOwner().getDni())) {
            throw new BadRequestException("Incomplete information");
        }
        this.carService.createWithOwnerDni(car.getOwner().getDni(), car);
    }

    @PutMapping(CarResource.LICENSE_PLATE + CarResource.OWNERS_DNI)
    public void updateOwner(@PathVariable("licensePlate") String licensePlate, @PathVariable("dni") String ownerDni) {
        this.carService.updateOwner(licensePlate, ownerDni);

    }

}
