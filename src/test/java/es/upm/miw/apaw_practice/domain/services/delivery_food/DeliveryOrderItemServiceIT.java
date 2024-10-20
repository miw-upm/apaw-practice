package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class DeliveryOrderItemServiceIT {

    @Autowired
    private DeliveryOrderItemService deliveryOrderItemService;

    @Test
    void testUpdateQuantity() {
        Integer quantity = 4;
        List<DeliveryOrderItem> orderItemList = deliveryOrderItemService.findAll();
        DeliveryOrderItem orderItemResponse = deliveryOrderItemService.updateQuantity(orderItemList.get(0).getId(), quantity);
        assertEquals(quantity, orderItemResponse.getQuantity());
    }

    @Test
    void testFindAll() {
        List<DeliveryOrderItem> orderItemList = deliveryOrderItemService.findAll();
        assertNotNull(orderItemList);
        assertNotNull(orderItemList.get(0));
        assertNotNull(orderItemList.get(0).getId());
        assertNotNull(orderItemList.get(0).getMenu());
        assertNotNull(orderItemList.get(0).getQuantity());
        assertNotNull(orderItemList.get(0).getPrice());
    }

    @Test
    void testFindDescriptionsMenuGreaterThanQuantity() {
        List<String> descriptionsMenuGreaterThanQuantity = deliveryOrderItemService.findDescriptionsMenuGreaterThanQuantity(5);
        assertNotNull(descriptionsMenuGreaterThanQuantity);
        assertEquals(2, descriptionsMenuGreaterThanQuantity.size());
    }
}