package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.TableEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TableRepository extends MongoRepository<TableEntity,String> {
    Optional<TableEntity> findByNumber(Integer number);
}
