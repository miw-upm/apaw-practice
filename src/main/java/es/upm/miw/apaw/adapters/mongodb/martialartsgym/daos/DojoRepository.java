package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface DojoRepository extends MongoRepository<DojoEntity, String> {
    Optional<DojoEntity> findByCity(String city);
}

