package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.WineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WineRepository extends MongoRepository<WineEntity, UUID> {
}
