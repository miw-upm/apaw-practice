package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DirectorRepository extends MongoRepository<DirectorEntity, String> {
    Optional<DirectorEntity> findByDni(String dni);
}
