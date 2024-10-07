package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface PieceRepository extends MongoRepository<PieceEntity, String>{

    Optional<PieceEntity> findByPartNumber(String partNumber );
}
