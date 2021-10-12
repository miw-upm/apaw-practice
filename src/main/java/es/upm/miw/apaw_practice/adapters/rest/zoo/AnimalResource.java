package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.models.zoo.CageCaretakerSurname;
import es.upm.miw.apaw_practice.domain.services.zoo.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(AnimalResource.ANIMALS)
public class AnimalResource {

    static final String ANIMALS = "/zoo/animals";
    static final String NAME = "/{name}";
    static final String CARETAKER_SURNAMES = "/cages/caretakers/surnames";

    private final AnimalService animalService;

    @Autowired
    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @DeleteMapping()
    public void delete(@RequestBody Animal animal) {
        this.animalService.delete(animal);
    }

    @GetMapping(NAME + CARETAKER_SURNAMES)
    public Stream<CageCaretakerSurname> findCaretakersSurnamesByAnimalName(@PathVariable String name) {
        return this.animalService.findCaretakersSurnamesByAnimalName(name);
    }
}
