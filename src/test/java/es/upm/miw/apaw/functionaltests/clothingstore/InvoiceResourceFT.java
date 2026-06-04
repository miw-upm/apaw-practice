package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.InvoiceRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.InvoiceResource;
import es.upm.miw.apaw.domain.models.clothingstore.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class InvoiceResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    @Autowired
    private InvoiceRepository invoiceRepository;

    private static final String SEEDED_NUMBER = "INV-2025-001";
    private static final String UNKNOWN_NUMBER = "INV-9999-999";

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testReadByNumber_ok() {
        Invoice invoice = this.webTestClient.get()
                .uri(InvoiceResource.INVOICES + "/" + SEEDED_NUMBER)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Invoice.class)
                .returnResult()
                .getResponseBody();

        assertThat(invoice).isNotNull();
        assertThat(invoice.getNumber()).isEqualTo(SEEDED_NUMBER);
    }

    @Test
    void testReadByNumber_notFound() {
        this.webTestClient.get()
                .uri(InvoiceResource.INVOICES + "/" + UNKNOWN_NUMBER)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete_ok() {
        assertThat(invoiceRepository.findById(SEEDED_NUMBER)).isPresent();

        this.webTestClient.delete()
                .uri(InvoiceResource.INVOICES + "/" + SEEDED_NUMBER)
                .exchange()
                .expectStatus().isNoContent();

        assertThat(invoiceRepository.findById(SEEDED_NUMBER)).isEmpty();
    }

    @Test
    void testDelete_notFound() {
        this.webTestClient.delete()
                .uri(InvoiceResource.INVOICES + "/" + UNKNOWN_NUMBER)
                .exchange()
                .expectStatus().isNotFound();
    }
}
