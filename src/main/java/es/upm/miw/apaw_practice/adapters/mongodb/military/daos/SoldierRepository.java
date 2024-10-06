package es.upm.miw.apaw_practice.adapters.mongodb.military.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.SoldierEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SoldierRepository extends MongoRepository<SoldierEntity, String> {
    List<SoldierEntity> findByIdentityDocumentIn(List<String> identityDocuments);
}
