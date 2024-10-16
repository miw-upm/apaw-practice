package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class DeliveryOrderItemPersistenceMongodbIT {

    @Autowired
    private DeliveryOrderItemPersistenceMongodb deliveryOrderItemPersistenceMongodb;

    @Test
    void testUpdateQuantity() {
        Integer quantity = 10;
        List<DeliveryOrderItem> orderItemList = deliveryOrderItemPersistenceMongodb.findAll();
        assertNotNull(orderItemList);
        DeliveryOrderItem orderItem = orderItemList.get(0);
        DeliveryOrderItem response = deliveryOrderItemPersistenceMongodb.updateQuantity(orderItem.getId(), quantity);
        assertEquals(orderItem.getId(), response.getId());
        assertEquals(quantity, response.getQuantity());
    }

    @Test
    void testFindAll() {
        List<DeliveryOrderItem> orderItemList = deliveryOrderItemPersistenceMongodb.findAll();
        assertNotNull(orderItemList);
        assertNotNull(orderItemList.get(0));
        assertNotNull(orderItemList.get(0).getId());
        assertNotNull(orderItemList.get(0).getMenu());
        assertNotNull(orderItemList.get(0).getQuantity());
        assertNotNull(orderItemList.get(0).getPrice());
    }

}