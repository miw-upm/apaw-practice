package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShippingAddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShippingAddressRepository extends MongoRepository<ShippingAddressEntity, String> {
    List<ShippingAddressEntity> findByLocation(String location);
}