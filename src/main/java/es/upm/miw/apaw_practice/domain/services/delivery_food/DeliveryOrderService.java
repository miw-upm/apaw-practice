package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.DeliveryOrderPersistence;
import org.springframework.stereotype.Service;

@Service
public class DeliveryOrderService {

    private final DeliveryOrderPersistence deliveryOrderPersistence;

    public DeliveryOrderService(DeliveryOrderPersistence deliveryOrderPersistence) {
        this.deliveryOrderPersistence = deliveryOrderPersistence;
    }

    public DeliveryOrder create(DeliveryOrder deliveryOrder){
        return deliveryOrderPersistence.create(deliveryOrder);
    }
}
