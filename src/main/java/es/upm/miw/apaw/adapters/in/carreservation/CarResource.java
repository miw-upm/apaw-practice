package es.upm.miw.apaw.adapters.in.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.services.carreservation.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CarResource.CARS)
@RequiredArgsConstructor
public class CarResource {
    public static final String CARS = "/cars";
    private final CarService carService;

    @PostMapping
    public Car create(@Valid @RequestBody Car car) {
        return this.carService.create(car);
    }
}
