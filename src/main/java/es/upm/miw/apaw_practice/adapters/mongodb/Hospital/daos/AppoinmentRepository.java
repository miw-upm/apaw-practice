package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppoinmentEntities;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface AppoinmentRepository extends MongoRepository<AppoinmentEntities, String> {

}
