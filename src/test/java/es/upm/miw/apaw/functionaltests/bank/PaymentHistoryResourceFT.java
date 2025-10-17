package es.upm.miw.apaw.functionaltests.bank;

import es.upm.miw.apaw.adapters.resources.bank.PaymentHistoryResource;
import es.upm.miw.apaw.domain.models.bank.PaymentHistoryUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PaymentHistoryResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdatePaymentHistoryPaid() {
        List<PaymentHistoryUpdating> updates = List.of(PaymentHistoryUpdating.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2000"))
                        .paid(true)
                        .build(),
                PaymentHistoryUpdating.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                        .paid(false)
                        .build());

        webTestClient.patch()
                .uri(PaymentHistoryResource.PAYMENT_HISTORY)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdatePaymentHistoryPaidNotFound() {
        List<PaymentHistoryUpdating> updates = List.of(PaymentHistoryUpdating.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff2100"))
                        .paid(true)
                        .build());
        webTestClient.patch()
                .uri(PaymentHistoryResource.PAYMENT_HISTORY)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isNotFound();
    }
}
