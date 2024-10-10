package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryOrderItemRepository extends MongoRepository<DeliveryOrderItemEntity, String> {
}
