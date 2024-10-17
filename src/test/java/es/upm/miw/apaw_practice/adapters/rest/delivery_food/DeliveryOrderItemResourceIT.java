package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.delivery_food.DeliveryOrderItemResource.DELIVERY_ORDER_ITEM;
import static es.upm.miw.apaw_practice.adapters.rest.delivery_food.DeliveryOrderItemResource.DELIVERY_ORDER_ITEM_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RestTestConfig
class DeliveryOrderItemResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateQuantity() {
        Integer quantity = 10;
        this.webTestClient
                .get()
                .uri(DELIVERY_ORDER_ITEM)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(DeliveryOrderItem.class)
                .value(Assertions::assertNotNull)
                .value(deliveryOrderItemList -> {
                    DeliveryOrderItem orderItem = deliveryOrderItemList.get(0);
                    this.webTestClient
                            .patch()
                            .uri(uriBuilder ->
                                    uriBuilder.path(DELIVERY_ORDER_ITEM + DELIVERY_ORDER_ITEM_ID)
                                            .queryParam("quantity", quantity)
                                            .build(orderItem.getId()))
                            .exchange()
                            .expectStatus().isOk()
                            .expectBody(DeliveryOrderItem.class)
                            .value(deliveryOrderItem -> {
                                assertEquals(quantity, deliveryOrderItem.getQuantity());
                            });
                });
    }

    @Test
    void testFindAll() {
        this.webTestClient
                .get()
                .uri(DELIVERY_ORDER_ITEM)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(DeliveryOrderItem.class)
                .value(Assertions::assertNotNull)
                .value(deliveryOrderItems -> {
                    assertNotNull(deliveryOrderItems);
                    assertNotNull(deliveryOrderItems.get(0));
                    assertNotNull(deliveryOrderItems.get(0).getId());
                    assertNotNull(deliveryOrderItems.get(0).getMenu());
                    assertNotNull(deliveryOrderItems.get(0).getQuantity());
                    assertNotNull(deliveryOrderItems.get(0).getPrice());
                });
    }
}