package es.upm.miw.apaw.functionaltests.expense;

import es.upm.miw.apaw.adapters.in.expense.ExpenseResource;
import es.upm.miw.apaw.config.seeders.ExpenseSeederForDev;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expense.Expense;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ExpenseResourceFT {

    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();

        UserSnapshot user = UserSnapshot.builder()
                .id(ExpenseSeederForDev.USER_1_ID)
                .mobile("600000100")
                .firstName("cliente0")
                .build();

        when(this.userFinder.findByIds(Set.of(ExpenseSeederForDev.USER_1_ID)))
                .thenReturn(List.of(user));
        when(this.userFinder.findByIds(any()))
                .thenReturn(List.of(user));
    }

    @Test
    void testFindWithCriteria() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ExpenseResource.EXPENSES)
                        .queryParam("category", "Office")
                        .queryParam("unpaid", true)
                        .queryParam("supplierTaxId", "B12345678")
                        .queryParam("userMobile", "600000100")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Expense[].class)
                .value(expenses -> {
                    assertThat(expenses).isNotNull();
                    assertThat(expenses).extracting(Expense::getReference)
                            .contains("EXP-SEED-001")
                            .doesNotContain("EXP-SEED-002");
                });
    }

    @Test
    void testFindAllUnfiltered() {
        this.webTestClient.get()
                .uri(ExpenseResource.EXPENSES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Expense[].class)
                .value(expenses -> {
                    assertThat(expenses).isNotNull();
                    assertThat(expenses.length).isGreaterThanOrEqualTo(2);
                });
    }
}