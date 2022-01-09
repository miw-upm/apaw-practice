package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.domain.models.training.TrainingItem;
import es.upm.miw.apaw_practice.domain.services.training.TrainingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(TrainingItemResource.TRAININGITEMS)
public class TrainingItemResource {
    static final String TRAININGITEMS = "/training/trainingItems";
    static final String ID_ID = "/{id}";
    static final String NAME = "/name";

    private final TrainingItemService trainingItemService;

    @Autowired
    public TrainingItemResource(TrainingItemService trainingItemService) {
        this.trainingItemService = trainingItemService;
    }

    @PutMapping(ID_ID + NAME)
    public TrainingItem updateName(@PathVariable String id, @RequestBody NameDto nameDto) {
        return this.trainingItemService.updateName(id, nameDto.getName());
    }

    @GetMapping
    public Stream<TrainingItem> readAll() {
        return this.trainingItemService.readAll();
    }
}
