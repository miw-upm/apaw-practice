package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.services.car.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CarResource.CARS)
public class CarResource {

    static final String CARS = "/cars";

    static final String MODEL = "/{model}";

    private CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @PostMapping()
    public ResponseEntity<Void> post(@RequestBody Car car) {

        carService.create(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(MODEL)
    public void delete(@PathVariable String model){
        this.carService.delete(model);
    }
}
