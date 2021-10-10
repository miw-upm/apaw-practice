package es.upm.miw.apaw_practice.domain.services.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.AnimalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalPersistence animalPersistence;

    @Autowired
    public AnimalService(AnimalPersistence animalPersistence) {
        this.animalPersistence = animalPersistence;
    }

    public void delete(Animal animal) {
        this.animalPersistence.delete(animal);
    }
}
