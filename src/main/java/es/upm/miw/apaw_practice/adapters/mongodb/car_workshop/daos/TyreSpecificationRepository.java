package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TyreSpecificationRepository extends MongoRepository<TyreSpecificationEntity, String> {
}
