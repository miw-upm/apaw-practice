package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DrugRepository extends MongoRepository<DrugEntity, String> {

}
