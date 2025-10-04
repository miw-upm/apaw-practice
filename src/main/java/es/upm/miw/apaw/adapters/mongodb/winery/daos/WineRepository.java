package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.WineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WineRepository extends MongoRepository<WineEntity, Long> {
}
