package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities.AnimalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnimalRepository extends MongoRepository<AnimalEntity, String> {

    Optional<AnimalEntity> findByName(String name);
}