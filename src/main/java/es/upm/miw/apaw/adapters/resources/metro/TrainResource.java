package es.upm.miw.apaw.adapters.resources.metro;

import es.upm.miw.apaw.domain.services.metro.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(TrainResource.TRAINS)
public class TrainResource {

    public static final String TRAINS = "/metro/trains";
    public static final String ID = "/{id}";
    private final TrainService trainService;

    @Autowired
    public TrainResource(TrainService trainService) {
        this.trainService = trainService;
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable("id") UUID id) {
        this.trainService.delete(id);
    }
}
