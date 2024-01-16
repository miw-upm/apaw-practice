package es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.entities.AcademyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.entities.InstructorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface InstructorRepository extends MongoRepository<InstructorEntity, String> {
    Optional<InstructorEntity> findByDni(String dni);

    Optional<InstructorEntity> findByName(String name);
}
