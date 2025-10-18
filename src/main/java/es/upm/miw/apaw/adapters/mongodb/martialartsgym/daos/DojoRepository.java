package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DojoRepository extends MongoRepository<DojoEntity, String> {
}
