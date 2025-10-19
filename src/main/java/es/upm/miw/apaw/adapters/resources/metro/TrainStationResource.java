package es.upm.miw.apaw.adapters.resources.metro;

import es.upm.miw.apaw.domain.services.metro.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(TrainStationResource.TRAIN_STATIONS)
public class TrainStationResource {

    public static final String TRAIN_STATIONS = "/metro/train-stations";
    public static final String NAME = "/{name}";
    public static final String CAPACITY = "/capacity";
    private final TrainStationService trainStationService;

    @Autowired
    public TrainStationResource(TrainStationService trainStationService) {
        this.trainStationService = trainStationService;
    }

    @GetMapping(NAME+CAPACITY)
    public Integer readCapacityByName(@PathVariable("name") String name) {
        return this.trainStationService.readCapacityByName(name);
    }

    @GetMapping(params = "mobile")
    public Stream<Integer> findNumCarsByUserMobile(@RequestParam String mobile) {
        return this.trainStationService.findNumCarsByUserMobile(mobile);
    }
}
