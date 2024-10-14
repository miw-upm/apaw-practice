package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.AnimalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.OwnerClinicRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.OwnerClinicEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.AnimalPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("animalPersistence")
public class AnimalPersistenceMongodb implements AnimalPersistence {

    private final AnimalRepository animalRepository;
    private final OwnerClinicRepository ownerClinicRepository;

    @Autowired
    public AnimalPersistenceMongodb(AnimalRepository animalRepository, OwnerClinicRepository ownerClinicRepository) {
        this.animalRepository = animalRepository;
        this.ownerClinicRepository = ownerClinicRepository;
    }

    @Override
    public Animal readByName(String name) {
        return this.animalRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Animal name: " + name))
                .toAnimal();
    }

    @Override
    public Animal update(Animal animal) {
        AnimalEntity animalEntity = this.animalRepository
                .findByName(animal.getName())
                .orElseThrow(() -> new NotFoundException("Animal name: " + animal.getName()));
        OwnerClinicEntity ownerClinicEntity = this.ownerClinicRepository
                .findByName(animal.getOwnerClinic().getName())
                .orElseGet(() -> {
                    OwnerClinicEntity newOwner = new OwnerClinicEntity(animal.getOwnerClinic());
                    return this.ownerClinicRepository.save(newOwner);
                });

        animalEntity.setAge(animal.getAge());
        animalEntity.setDateOfService(animal.getDateOfService());
        animalEntity.setOwnerEntity(ownerClinicEntity);
        return this.animalRepository.save(animalEntity).toAnimal();
    }
}