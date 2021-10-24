package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.ActiveIngredientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DrugEntity;
import es.upm.miw.apaw_practice.domain.models.pharmacy.ActiveIngredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface ActiveIngredientRepository extends MongoRepository<ActiveIngredientEntity, String> {

    Optional<ActiveIngredientEntity> findByCode(String code);

    Stream<ActiveIngredientEntity> findByDrugEntity(DrugEntity drug);
}
