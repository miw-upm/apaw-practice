package es.upm.miw.apaw.functionaltests.apiary;

import es.upm.miw.apaw.adapters.resources.apiary.SaleResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.apiary.Sale;
import es.upm.miw.apaw.domain.services.apiary.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SaleResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private SaleService saleService;

    @Test
    void testCreateSale() {
        UserDto client = new UserDto(UUID.randomUUID(), "123456789", "John");

        Sale sale = Sale.builder()
                .idSale(1)
                .paymentForm(1)
                .shippingAddress("Calle Falsa 123")
                .amount(new BigDecimal("25.50"))
                .client(client)
                .build();

        given(saleService.create(any(Sale.class))).willReturn(sale);

        webTestClient.post()
                .uri(SaleResource.SALES)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(sale), Sale.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idSale").isEqualTo(1)
                .jsonPath("$.paymentForm").isEqualTo(1)
                .jsonPath("$.shippingAddress").isEqualTo("Calle Falsa 123")
                .jsonPath("$.amount").isEqualTo(25.50)
                .jsonPath("$.client.mobile").isEqualTo("123456789")
                .jsonPath("$.client.firstName").isEqualTo("John");
    }

    @Test
    void testDeleteSale() {
        int idSale = 1;

        willDoNothing().given(saleService).delete(eq(idSale));

        webTestClient.delete()
                .uri(SaleResource.SALES + "/" + idSale)
                .exchange()
                .expectStatus().isOk();
    }
}
