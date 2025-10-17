package es.upm.miw.apaw.functionaltests.warehouse;

import es.upm.miw.apaw.adapters.resources.warehouse.ProductItemResource;
import es.upm.miw.apaw.domain.models.warehouse.ProductItem;
import org.junit.jupiter.api.Test;
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
        ProductItem updated = new ProductItem();
        updated.setAppoint("Updated Screw 12mm");
        updated.setCost(new BigDecimal("0.25"));
        updated.setUnitOfMeasure("UNIT");

        this.webTestClient.put()
                .uri(ProductItemResource.PRODUCT_ITEMS + ProductItemResource.BARCODE, "PI-001")
                .bodyValue(updated)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductItem.class)
                .value(item -> {
                    assertThat(item.getAppoint()).isEqualTo("Updated Screw 12mm");
                    assertThat(item.getCost()).isEqualByComparingTo("0.25");
                    assertThat(item.getUnitOfMeasure()).isEqualTo("UNIT");
                });
    }

}
