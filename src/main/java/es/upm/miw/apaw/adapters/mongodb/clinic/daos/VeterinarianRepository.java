package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.VeterinarianEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeterinarianRepository extends MongoRepository<VeterinarianEntity, String> {

}