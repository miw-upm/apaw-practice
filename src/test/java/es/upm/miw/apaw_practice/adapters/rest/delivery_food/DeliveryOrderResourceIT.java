package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class DeliveryOrderResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        DeliveryOrder orderInsert = getDeliveryOrderInsert();
        this.webTestClient
                .post()
                .uri(DeliveryOrderResource.DELIVERY_ORDER)
                .body(BodyInserters.fromValue(orderInsert))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(DeliveryOrder.class)
                .value(order -> {
                    assertNotNull(order.getId());
                    assertEquals(orderInsert.getDelivered(), order.getDelivered());
                    assertEquals(orderInsert.getDeliveryAddress(), order.getDeliveryAddress());
                    assertNotNull(order.getDeliveryOrderItems());
                    assertNotNull(order.getDeliveryOrderItems().get(0).getMenu());

                });
    }

    private DeliveryOrder getDeliveryOrderInsert(){
        DeliveryOrder order = new DeliveryOrder();
        order.setDelivered(Boolean.TRUE);
        order.setDeliveryAddress("av 22");
        order.setCustomerName("Customer 1010");
        order.setDeliveryOrderItems(mapDeliveryOrderItems());
        return order;
    }

    private List<DeliveryOrderItem> mapDeliveryOrderItems() {
        DeliveryOrderItem orderItem = new DeliveryOrderItem();
        orderItem.setPrice(new BigDecimal("4.4"));
        orderItem.setQuantity(7);
        orderItem.setMenu(mapMenu());
        return List.of(orderItem);
    }

    private Menu mapMenu() {
        Menu menu = new Menu();
        menu.setName("Italian Feast");
        return menu;
    }
}