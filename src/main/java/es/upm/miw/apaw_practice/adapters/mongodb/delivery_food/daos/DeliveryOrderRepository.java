package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeliveryOrderRepository extends MongoRepository<DeliveryOrderEntity, String> {

    List<DeliveryOrderEntity> findByCustomerName(String customerName);
}
