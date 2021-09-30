package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.ZooEntity;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ZooRepository extends MongoRepository<ZooEntity, String> {

    Optional<ZooEntity> findByAddress(ZooAddress address);
}
