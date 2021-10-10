package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.services.zoo.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AnimalResource.ANIMALS)
public class AnimalResource {

    static final String ANIMALS = "/zoo/animals";

    private final AnimalService animalService;

    @Autowired
    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @DeleteMapping()
    public void delete(@RequestBody Animal animal) {
        this.animalService.delete(animal);
    }
}
