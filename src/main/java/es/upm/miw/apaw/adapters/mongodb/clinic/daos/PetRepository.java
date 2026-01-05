package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.PetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PetRepository extends MongoRepository<PetEntity, String> {

    Optional<PetEntity> findByMicrochipNumber(Long microchipNumber);

}
