package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.ProducerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProducerRepository extends MongoRepository<ProducerEntity,String> {
    Optional<ProducerEntity> findByBusinessName(String businessName);
}
