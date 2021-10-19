package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DrugRepository extends MongoRepository<DrugEntity, String> {
    Optional<DrugEntity> findByBarcode(String barcode);
}
