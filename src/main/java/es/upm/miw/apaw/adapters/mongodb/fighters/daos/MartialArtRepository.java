package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.MartialArtEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MartialArtRepository extends MongoRepository<MartialArtEntity, String> {
}
