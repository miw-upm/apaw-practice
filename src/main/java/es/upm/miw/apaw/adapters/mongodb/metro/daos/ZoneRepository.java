package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.ZoneEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ZoneRepository extends MongoRepository<ZoneEntity, UUID> {
}

