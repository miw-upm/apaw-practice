package es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.entities.ShippingAddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ShippingAddressRepository extends MongoRepository<ShippingAddressEntity, String> {
    Optional<ShippingAddressEntity> findByLocation(String location);
}