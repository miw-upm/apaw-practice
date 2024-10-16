package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.DeliveryOrderItemRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.DeliveryOrderRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.MenuRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.DeliveryOrderItemEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.DeliveryOrderPersistence;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository("deliveryOrderPersistenceMongodb")
public class DeliveryOrderPersistenceMongodb implements DeliveryOrderPersistence {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryOrderItemRepository deliveryOrderItemRepository;
    private final MenuRepository menuRepository;

    public DeliveryOrderPersistenceMongodb(DeliveryOrderRepository deliveryOrderRepository,
                                           DeliveryOrderItemRepository deliveryOrderItemRepository,
                                           MenuRepository menuRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.deliveryOrderItemRepository = deliveryOrderItemRepository;
        this.menuRepository = menuRepository;
    }

    public DeliveryOrder create(DeliveryOrder order) {
        List<DeliveryOrderItemEntity> deliveryOrderItemEntitySave = getDeliveryOrderItemEntitySave(order.getDeliveryOrderItems());
        DeliveryOrderEntity orderEntity = new DeliveryOrderEntity(
                order.getDeliveryAddress(), order.getCustomerName(), LocalDateTime.now(), order.getDelivered(), deliveryOrderItemEntitySave
        );
        return deliveryOrderRepository.save(orderEntity).toDeliveryOrder();
    }

    private List<DeliveryOrderItemEntity> getDeliveryOrderItemEntitySave(List<DeliveryOrderItem> orderItems) {
        if (orderItems == null) {
            throw new NotFoundException("DeliveryOrderItems not exist");
        }
        return orderItems
                .stream()
                .map(item -> {
                    MenuEntity menuEntity = getMenuEntity(item);
                    DeliveryOrderItemEntity itemEntity = new DeliveryOrderItemEntity(
                            item.getPrice(), item.getQuantity(), menuEntity);
                    return deliveryOrderItemRepository.save(itemEntity);
                }).toList();
    }

    private MenuEntity getMenuEntity(DeliveryOrderItem item) {
        String menuName = item.getMenu().getName();
        return menuRepository.findByName(menuName)
                .orElseThrow(() -> new NotFoundException("Menu name: " + menuName));
    }
}
