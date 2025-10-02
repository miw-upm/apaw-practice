package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ApplicationRepository extends MongoRepository<ApplicationEntity, UUID> {
}
