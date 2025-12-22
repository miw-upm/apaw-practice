package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.PetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<PetEntity, String> {

}
