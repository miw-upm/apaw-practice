package es.upm.miw.apaw_practice.adapters.mongodb.zoo.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.AnimalRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.AnimalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("animalPersistence")
public class AnimalPersistenceMongodb implements AnimalPersistence {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalPersistenceMongodb(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void delete(Animal animal) {
        this.animalRepository.delete(this.animalRepository.findByName(animal.getName())
                .orElseThrow(() -> new NotFoundException("Animal with name " + animal.getName())));
    }

    @Override
    public Animal findByName(String name) {
        return this.animalRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Animal with name " + name))
                .toAnimal();
    }
}
