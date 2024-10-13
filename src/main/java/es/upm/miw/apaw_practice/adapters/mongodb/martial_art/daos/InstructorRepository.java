package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;



import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.InstructorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InstructorRepository extends MongoRepository<InstructorEntity, String> {
    Optional<InstructorEntity> findByDni(String dni);
    void deleteByDni(String dni);
}
