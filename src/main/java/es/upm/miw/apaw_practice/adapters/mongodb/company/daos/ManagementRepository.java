package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ManagementEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ManagementRepository extends MongoRepository<ManagementEntity, String> {
}

