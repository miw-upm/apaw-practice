package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.services.veterinary_clinic.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AnimalResource.ANIMALS)
public class AnimalResource {

    static final String ANIMALS = "/veterinary-clinic/animals";
    static final String ID_NAME = "/{name}";

    private final AnimalService animalService;

    @Autowired
    public AnimalResource(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PutMapping(ID_NAME)
    public Animal update(@PathVariable String name, @RequestBody Animal animal) {
        return this.animalService.update(name, animal);
    }
}