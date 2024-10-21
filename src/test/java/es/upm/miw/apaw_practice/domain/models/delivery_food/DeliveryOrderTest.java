package es.upm.miw.apaw_practice.domain.models.delivery_food;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class DeliveryOrderTest {

    @Test
    void testBuild(){
        String address = "Address 1";
        String customerName = "Customer 1";
        LocalDateTime orderDate = LocalDateTime.now();
        Boolean delivered = Boolean.FALSE;

        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .id(UUID.randomUUID().toString())
                .deliveryAddress(address)
                .customerName(customerName)
                .orderDate(orderDate)
                .delivered(delivered)
                .build();

        assertNotNull(deliveryOrder);
        assertEquals(address, deliveryOrder.getDeliveryAddress());
        assertEquals(customerName, deliveryOrder.getCustomerName());
        assertEquals(orderDate, deliveryOrder.getOrderDate());
        assertFalse(deliveryOrder.getDelivered());
        assertNull(deliveryOrder.getDeliveryOrderItems());
    }

    @Test
    void testBuilderWithOptionals() {
        String address = "Address 1";
        String customerName = "Customer 1";
        LocalDateTime orderDate = LocalDateTime.now();
        Boolean delivered = Boolean.FALSE;

        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .id(UUID.randomUUID().toString())
                .deliveryAddress(address)
                .customerName(customerName)
                .orderDate(orderDate)
                .delivered(delivered)
                .deliveryOrderItems(new ArrayList<>())
                .build();

        assertNotNull(deliveryOrder);
        assertEquals(address, deliveryOrder.getDeliveryAddress());
        assertEquals(customerName, deliveryOrder.getCustomerName());
        assertEquals(orderDate, deliveryOrder.getOrderDate());
        assertFalse(deliveryOrder.getDelivered());
        assertNotNull(deliveryOrder.getDeliveryOrderItems());
    }
}
