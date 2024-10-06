package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.OperatorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperatorRepository extends MongoRepository<OperatorEntity, String> {

}
