package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.adapters.rest.shop.ArticleResource;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.services.car_workshop.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static java.util.Objects.isNull;

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
        if (Objects.isNull(car.getLicensePlate()) ||
                Objects.isNull(car.getOwner().getDni())){
            throw new BadRequestException("Incomplete information");
        }
        this.carService.createWithOwnerDni(car.getOwner().getDni(), car);
    }

}
