package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.services.veterinary_clinic.AnimalService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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