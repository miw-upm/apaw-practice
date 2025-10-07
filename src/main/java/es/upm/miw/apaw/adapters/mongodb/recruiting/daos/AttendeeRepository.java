package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttendeeRepository extends MongoRepository<AttendeeEntity, UUID> {

    Optional<AttendeeEntity> findByEmailAddress(String emailAddress);

}
