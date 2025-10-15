package es.upm.miw.apaw.adapters.mongodb.football.daos;

import es.upm.miw.apaw.adapters.mongodb.football.entities.StadiumEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StadiumRepository extends MongoRepository<StadiumEntity, Long> {

    Optional<StadiumEntity> findByOfficialNameIgnoreCase(String officialName);

    boolean existsByOfficialNameIgnoreCase(String officialName);
}
