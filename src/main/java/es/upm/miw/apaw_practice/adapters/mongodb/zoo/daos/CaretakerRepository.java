package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CaretakerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CaretakerRepository extends MongoRepository<CaretakerEntity, String> {

    Optional<CaretakerEntity> findByDni(String dni);
}
