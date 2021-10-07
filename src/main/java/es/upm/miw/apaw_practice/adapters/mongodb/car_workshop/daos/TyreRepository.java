package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TyreRepository extends MongoRepository<TyreEntity, String> {

    void deleteByManufacturer(String manufacturer);

}
