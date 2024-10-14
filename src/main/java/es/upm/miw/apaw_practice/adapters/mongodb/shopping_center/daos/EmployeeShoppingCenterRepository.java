package es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.EmployeeShoppingCenterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeShoppingCenterRepository extends MongoRepository<EmployeeShoppingCenterEntity, String> {
    Optional<EmployeeShoppingCenterEntity> findByDni(String dni);
}
