package es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;

import java.util.List;

public interface DeliveryOrderItemPersistence {

    DeliveryOrderItem updateQuantity(String id, Integer quantity);

    List<DeliveryOrderItem> findAll();
}
