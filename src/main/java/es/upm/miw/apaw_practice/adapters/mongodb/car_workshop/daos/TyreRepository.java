package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TyreRepository extends MongoRepository<TyreEntity, String> {

    void deleteByManufacturer(String manufacturer);

    Optional<TyreEntity> findByModel(String model);

    List<Tyre> findByTyreSpecsEntity(TyreSpecificationEntity tyreSpecification);
}
