package es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;

public interface DeliveryOrderPersistence {

    DeliveryOrder create(DeliveryOrder order);
}
