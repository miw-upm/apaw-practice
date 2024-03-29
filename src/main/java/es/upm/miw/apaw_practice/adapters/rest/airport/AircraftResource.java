package es.upm.miw.apaw_practice.adapters.rest.airport;

import es.upm.miw.apaw_practice.domain.services.airport.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AircraftResource.AIRCRAFTS)
public class AircraftResource {
    static final String AIRCRAFTS = "/airport/aircrafts";
    static final String NUMBER_PLATE = "/{numberplate}";
    private final AircraftService aircraftService;

    @Autowired
    public AircraftResource(AircraftService aircraftService){
        this.aircraftService = aircraftService;
    }
    @DeleteMapping(NUMBER_PLATE)
    public void delete(@PathVariable String numberplate){
        this.aircraftService.delete(numberplate);
    }
}
