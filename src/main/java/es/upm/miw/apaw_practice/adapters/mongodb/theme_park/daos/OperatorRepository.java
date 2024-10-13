package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.OperatorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OperatorRepository extends MongoRepository<OperatorEntity, String> {
    Optional<OperatorEntity> findByIdEmployee(String idEmployee);

    void deleteByIdEmployee(String idEmployee);
}
