package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.domain.services.car.CarService;
import es.upm.miw.apaw_practice.domain.services.car.ManufacturerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CarResource.CARS)
public class CarResource {

    static final String CARS = "/cars";

    static final String MODEL = "/{model}";

    private CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @DeleteMapping(MODEL)
    public void delete(@PathVariable String model){
        this.carService.delete(model);
    }
}
