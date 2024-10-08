package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppoinmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppoinmentRepository extends MongoRepository<AppoinmentEntity, String> {
}
