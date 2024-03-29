package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.VaccineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VaccineRepository extends MongoRepository<VaccineEntity, String> {
    Optional<VaccineEntity> findByIdentifierBatch(String identifierBatch);

}
