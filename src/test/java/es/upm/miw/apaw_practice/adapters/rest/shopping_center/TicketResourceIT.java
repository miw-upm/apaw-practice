package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

@RestTestConfig
class TicketResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(TicketResource.TICKETS + TicketResource.ID_ID, "nn")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateTotalPrice() {
        List<Ticket> tickets = this.webTestClient
                .get()
                .uri(TicketResource.TICKETS)
                .exchange()
                .expectBodyList(Ticket.class)
                .returnResult()
                .getResponseBody();
        assert tickets != null;
        Ticket ticket = tickets.get(0);
        BigDecimal newTotalValue = new BigDecimal("99.99");
        this.webTestClient
                .patch()
                .uri(TicketResource.TICKETS + TicketResource.ID_ID, ticket.getId())
                .body(BodyInserters.fromValue(newTotalValue))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testSumTotalPriceOfMainService() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(TicketResource.TICKETS + TicketResource.SEARCH)
                                .queryParam("q", "mainService:tools")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testSumTotalPriceOfMainServiceBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(TicketResource.TICKETS + TicketResource.SEARCH)
                                .queryParam("q", "secondService:food")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}
