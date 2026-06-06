package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterHallEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface TheaterHallRepository extends MongoRepository<TheaterHallEntity, UUID> {
    Optional<TheaterHallEntity> findByHallCode(String hallCode);

    int deleteByHallCode(String hallCode);
}
