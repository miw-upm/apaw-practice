package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.CustomerECommerceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ECommerceCustomerRepository extends MongoRepository<CustomerECommerceEntity, String> {
    Optional<CustomerECommerceEntity> findByUserName(String userName);
}
