package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.DeliveryOrderItemRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderItemEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.DeliveryOrderItemPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deliveryOrderItemPersistenceMongodb")
public class DeliveryOrderItemPersistenceMongodb implements DeliveryOrderItemPersistence {

    private final DeliveryOrderItemRepository deliveryOrderItemRepository;

    public DeliveryOrderItemPersistenceMongodb(DeliveryOrderItemRepository deliveryOrderItemRepository) {
        this.deliveryOrderItemRepository = deliveryOrderItemRepository;
    }

    @Override
    public DeliveryOrderItem updateQuantity(String id, Integer quantity) {
        DeliveryOrderItemEntity orderItemEntity = deliveryOrderItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DeliveryOrderItem not found: " + id));
        orderItemEntity.setQuantity(quantity);
        return deliveryOrderItemRepository.save(orderItemEntity).toDeliveryOrderItem();
    }

    @Override
    public List<DeliveryOrderItem> findAll() {
        return deliveryOrderItemRepository.findAll()
                .stream()
                .map(DeliveryOrderItemEntity::toDeliveryOrderItem)
                .toList();
    }
}
