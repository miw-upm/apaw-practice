package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.DeliveryOrderItemPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryOrderItemService {

    private final DeliveryOrderItemPersistence deliveryOrderPersistence;

    public DeliveryOrderItemService(DeliveryOrderItemPersistence deliveryOrderPersistence) {
        this.deliveryOrderPersistence = deliveryOrderPersistence;
    }

    public DeliveryOrderItem updateQuantity(String id, Integer quantity) {
        return deliveryOrderPersistence.updateQuantity(id, quantity);
    }

    public List<DeliveryOrderItem> findAll() {
        return deliveryOrderPersistence.findAll();
    }
}
