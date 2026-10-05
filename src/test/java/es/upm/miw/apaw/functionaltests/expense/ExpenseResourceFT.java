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

        UserSnapshot user0 = ExpenseSeederForDev.EXPENSE_0.getUserSnapshot();
        UserSnapshot user1 = ExpenseSeederForDev.EXPENSE_1.getUserSnapshot();

        when(this.userFinder.findByIds(Set.of(user0.getId(), user1.getId())))
                .thenReturn(List.of(user0, user1));
        when(this.userFinder.findByIds(any()))
                .thenReturn(List.of(user0, user1));
    }

    @Test
    void testFindWithCriteria() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ExpenseResource.EXPENSES)
                        .queryParam("category", "Office")
                        .queryParam("unpaid", true)
                        .queryParam("supplierTaxId", ExpenseSeederForDev.SUPPLIER_0.getTaxId())
                        .queryParam("userMobile", "600000100")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Expense[].class)
                .value(expenses -> {
                    assertThat(expenses).isNotNull();
                    assertThat(expenses).extracting(Expense::getReference)
                            .contains(ExpenseSeederForDev.EXPENSE_0.getReference())
                            .doesNotContain(ExpenseSeederForDev.EXPENSE_1.getReference());
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
                    assertThat(expenses.length).isGreaterThanOrEqualTo(3);
                });
    }
}