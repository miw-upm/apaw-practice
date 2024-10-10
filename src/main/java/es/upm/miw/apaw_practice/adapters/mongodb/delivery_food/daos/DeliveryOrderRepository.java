package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryOrderRepository extends MongoRepository<DeliveryOrderEntity, String> {
}
