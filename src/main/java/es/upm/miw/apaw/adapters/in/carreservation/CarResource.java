package es.upm.miw.apaw.adapters.in.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.services.carreservation.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(CarResource.CARS)
@RequiredArgsConstructor
public class CarResource {
    public static final String CARS = "/cars";
    public static final String ID = "/{id}";
    private final CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@Valid @RequestBody Car car) {
        return this.carService.create(car);
    }

    @GetMapping(ID)
    public Car read(@PathVariable UUID id) {
        return this.carService.read(id);
    }

    @PutMapping(ID)
    public Car update(@PathVariable UUID id,
                      @Valid @RequestBody Car car) {
        return this.carService.update(id, car);
    }
}
