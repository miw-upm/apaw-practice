package es.upm.miw.apaw_practice.adapters.mongodb.zoo.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.AnimalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.AnimalEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import es.upm.miw.apaw_practice.domain.persistence_ports.zoo.AnimalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Repository("animalPersistence")
public class AnimalPersistenceMongodb implements AnimalPersistence {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalPersistenceMongodb(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void delete(Animal animal) {
        Example<AnimalEntity> exampleAnimal = this.exampleOf(animal);
        if (this.animalRepository.exists(exampleAnimal)) {
            this.animalRepository.delete(new AnimalEntity(animal));
        } else {
            throw new NotFoundException("Not found: " + animal.toString());
        }
    }

    private Example<AnimalEntity> exampleOf(Animal animal) {
        ExampleMatcher animalMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ignoreCase())
                .withMatcher("family", ignoreCase());
        return Example.of(new AnimalEntity(animal), animalMatcher);
    }
}
