package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.DeliveryOrderPersistence;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DeliveryOrderService {

    private final DeliveryOrderPersistence deliveryOrderPersistence;

    public DeliveryOrderService(DeliveryOrderPersistence deliveryOrderPersistence) {
        this.deliveryOrderPersistence = deliveryOrderPersistence;
    }

    public DeliveryOrder create(DeliveryOrder deliveryOrder){
        return deliveryOrderPersistence.create(deliveryOrder);
    }

    public Double calculateTotalRating(String customerName, String menuDescription) {
        return deliveryOrderPersistence.find(customerName)
                .stream()
                .flatMap(order -> order.getDeliveryOrderItems().stream())
                .flatMap(item -> Stream.of(item.getMenu()))
                .filter(menu -> menu.getDescription().equals(menuDescription))
                .mapToDouble(Menu::getRating)
                .distinct()
                .reduce(0.0, Double::sum);
    }
}