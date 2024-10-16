package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class DeliveryOrderServiceIT {

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @Test
    void testCreate(){
        DeliveryOrder deliveryOrderInsert = getDeliveryOrderInsert();
        DeliveryOrder deliveryOrderSave = deliveryOrderService.create(deliveryOrderInsert);

        assertNotNull(deliveryOrderSave);
        assertEquals(deliveryOrderInsert.getDelivered(), deliveryOrderSave.getDelivered());
        assertEquals(deliveryOrderInsert.getDeliveryAddress(), deliveryOrderSave.getDeliveryAddress());
        assertNotNull(deliveryOrderInsert.getDeliveryOrderItems());
        assertNotNull(deliveryOrderInsert.getDeliveryOrderItems().get(0).getMenu());
    }

    private DeliveryOrder getDeliveryOrderInsert(){
        DeliveryOrder order = new DeliveryOrder();
        order.setDelivered(Boolean.FALSE);
        order.setDeliveryAddress("Add 001 001");
        order.setCustomerName("Customer 01");
        order.setDeliveryOrderItems(mapDeliveryOrderItems());
        return order;
    }

    private List<DeliveryOrderItem> mapDeliveryOrderItems() {
        DeliveryOrderItem orderItem = new DeliveryOrderItem();
        orderItem.setPrice(new BigDecimal("8.4"));
        orderItem.setQuantity(3);
        orderItem.setMenu(mapMenu());
        return List.of(orderItem);
    }

    private Menu mapMenu() {
        Menu menu = new Menu();
        menu.setName("Italian Feast");
        return menu;
    }

}