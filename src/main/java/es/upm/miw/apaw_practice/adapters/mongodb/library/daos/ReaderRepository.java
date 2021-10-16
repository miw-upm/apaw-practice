package es.upm.miw.apaw_practice.adapters.mongodb.library.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.ReaderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReaderRepository extends MongoRepository<ReaderEntity, String> {
}
