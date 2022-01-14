package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.domain.services.training.TrainingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TrainingItemResource.TRAININGITEMS)
public class TrainingItemResource {
    static final String TRAININGITEMS = "/training/trainingItems";
    static final String NAME = "/{name}";

    private final TrainingItemService trainingItemService;

    @Autowired
    public TrainingItemResource(TrainingItemService trainingItemService) {
        this.trainingItemService = trainingItemService;
    }

    @DeleteMapping(NAME)
    public void delete(@PathVariable String name) {
        this.trainingItemService.delete(name);
    }
}
