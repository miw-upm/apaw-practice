package es.upm.miw.apaw.functionaltests.warehouse;

import es.upm.miw.apaw.adapters.resources.warehouse.ProductItemResource;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import es.upm.miw.apaw.domain.services.warehouse.ProductItemService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ProductItemResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateProductItem() {
        ProductItemService mockService = Mockito.mock(ProductItemService.class);
        WebTestClient localClient = WebTestClient.bindToController(
                new ProductItemResource(mockService)
        ).build();

        String barcode = "PI-001";
        ProductItem input = ProductItem.builder()
                .appoint("Updated Screw 12mm")
                .cost(new BigDecimal("0.25"))
                .unitOfMeasure("UNIT")
                .build();

        BDDMockito.given(mockService.update(barcode, input)).willReturn(input);

        localClient.put()
                .uri(ProductItemResource.PRODUCT_ITEMS + ProductItemResource.BARCODE, barcode)
                .bodyValue(input)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductItem.class)
                .value(item -> {
                    assertThat(item.getAppoint()).isEqualTo("Updated Screw 12mm");
                    assertThat(item.getCost()).isEqualByComparingTo("0.25");
                });

        Mockito.verify(mockService).update(barcode, input);
    }

}
