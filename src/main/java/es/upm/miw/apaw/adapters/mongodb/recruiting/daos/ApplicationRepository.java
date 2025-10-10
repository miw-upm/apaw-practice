package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ApplicationRepository extends MongoRepository<ApplicationEntity, UUID> {
    List<ApplicationEntity> findByStatus(Status status);
    List<ApplicationEntity> findByUser(UUID user);
    List<ApplicationEntity> findAllByOrderByCreatedAsc();
}