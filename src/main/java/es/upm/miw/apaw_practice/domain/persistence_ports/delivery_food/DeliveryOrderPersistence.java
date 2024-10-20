package es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface DeliveryOrderPersistence {

    DeliveryOrder create(DeliveryOrder order);

    List<DeliveryOrder> find(@NotNull String customerName);

}
