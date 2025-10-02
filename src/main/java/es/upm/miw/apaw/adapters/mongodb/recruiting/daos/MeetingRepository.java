package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MeetingRepository extends MongoRepository<MeetingEntity, UUID> {
}
