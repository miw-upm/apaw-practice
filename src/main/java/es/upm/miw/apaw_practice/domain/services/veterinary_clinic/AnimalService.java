package es.upm.miw.apaw_practice.domain.services.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.AnimalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalPersistence animalPersistence;

    @Autowired
    public AnimalService(AnimalPersistence animalPersistence) {
        this.animalPersistence = animalPersistence;
    }

    public Animal update(String name, Animal animal) {
        Animal existingAnimal = this.animalPersistence.readByName(name);

        existingAnimal.setAge(animal.getAge());
        existingAnimal.setDateOfService(animal.getDateOfService());
        existingAnimal.setOwnerClinic(animal.getOwnerClinic());
        return this.animalPersistence.update(existingAnimal);
    }
}