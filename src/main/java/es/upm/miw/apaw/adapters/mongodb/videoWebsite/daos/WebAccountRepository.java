package es.upm.miw.apaw.adapters.mongodb.videoWebsite.daos;

import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.VideoEntity;
import es.upm.miw.apaw.adapters.mongodb.videoWebsite.entities.WebAccountEntity;
import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface WebAccountRepository extends MongoRepository<WebAccountEntity, UUID> {
    List<WebAccountEntity> findByUserId(UUID userId);
}
