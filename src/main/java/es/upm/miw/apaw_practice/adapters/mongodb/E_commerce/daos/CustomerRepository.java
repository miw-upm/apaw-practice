package es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.entities.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByUserName(String userName);
}
