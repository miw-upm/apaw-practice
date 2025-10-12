package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.WebAccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WebAccountRepository extends MongoRepository<WebAccountEntity, UUID> {
}
