package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.VetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VetRepository extends MongoRepository<VetEntity, String> {
    Optional<VetEntity> findVetByVetNumber(Integer vetNumber);
}
