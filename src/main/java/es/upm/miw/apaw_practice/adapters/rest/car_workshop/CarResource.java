package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.services.car_workshop.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CarResource.CARS)
public class CarResource {
    static final String CARS = "/car-workshop/cars";

    private final CarService carService;

    @Autowired
    public CarResource(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    public void create(@RequestBody Car car){
        car.doDefault();
        this.carService.create(car);
    }

}
