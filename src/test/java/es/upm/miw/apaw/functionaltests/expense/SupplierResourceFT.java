package es.upm.miw.apaw.functionaltests.expense;

import es.upm.miw.apaw.adapters.in.expense.SupplierResource;
import es.upm.miw.apaw.config.seeders.ExpenseSeederForDev;
import es.upm.miw.apaw.domain.model.expense.Supplier;
import es.upm.miw.apaw.domain.model.expense.SupplierExpenseReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class SupplierResourceFT {

    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
    }

    @Test
    void testReadSuccess() {
        this.webTestClient.get()
                .uri(SupplierResource.SUPPLIERS + SupplierResource.ID_ID, ExpenseSeederForDev.SUPPLIER_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Supplier.class)
                .value(supplier -> assertEquals("B12345678", supplier.getTaxId()));
    }

    @Test
    void testReadNotFound() {
        this.webTestClient.get()
                .uri(SupplierResource.SUPPLIERS + SupplierResource.ID_ID, "00000000-0000-0000-0000-000000000000")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateSuccess() {
        Supplier supplier = Supplier.builder()
                .taxId("C99999999")
                .companyName("New Test Supplier")
                .build();

        this.webTestClient.post()
                .uri(SupplierResource.SUPPLIERS)
                .bodyValue(supplier)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Supplier.class)
                .value(created -> {
                    assertNotNull(created.getId());
                    assertEquals("C99999999", created.getTaxId());
                });
    }

    @Test
    void testCreateConflict() {
        Supplier supplier = Supplier.builder()
                .taxId("B12345678")
                .companyName("Duplicate Tax Supplier")
                .build();

        this.webTestClient.post()
                .uri(SupplierResource.SUPPLIERS)
                .bodyValue(supplier)
                .exchange()
                .expectStatus().isEqualTo(409);
    }

    @Test
    void testPatchSuccess() {
        Supplier patchSupplier = Supplier.builder()
                .address("Calle Nueva 99")
                .build();

        this.webTestClient.patch()
                .uri(SupplierResource.SUPPLIERS + SupplierResource.ID_ID, ExpenseSeederForDev.SUPPLIER_ID_1)
                .bodyValue(patchSupplier)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Supplier.class)
                .value(supplier -> assertEquals("Calle Nueva 99", supplier.getAddress()));
    }

    @Test
    void testFindExpenseReport() {
        this.webTestClient.get()
                .uri(SupplierResource.SUPPLIERS + SupplierResource.REPORT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SupplierExpenseReport[].class)
                .value(reports -> {
                    assertThat(reports).isNotNull();
                    assertThat(reports.length).isGreaterThanOrEqualTo(1);
                });
    }
}