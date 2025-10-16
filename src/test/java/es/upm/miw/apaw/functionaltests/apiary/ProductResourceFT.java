package es.upm.miw.apaw.functionaltests.apiary;

import es.upm.miw.apaw.adapters.resources.apiary.ProductResource;
import es.upm.miw.apaw.domain.models.apiary.Product;
import es.upm.miw.apaw.domain.models.apiary.ProductPriceUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")


public class ProductResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateProduct() {
        Product product = Product.builder()
                .barcode("P001")
                .product("Miel de Romero")
                .price(new BigDecimal("9.99"))
                .sales(List.of()) // o una lista vacía si no es relevante
                .build();

        webTestClient.put()
                .uri(ProductResource.PRODUCTS + ProductResource.BARCODE_ID, product.getBarcode())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .value(updated -> {
                    assertThat(updated.getBarcode()).isEqualTo("P001");
                    assertThat(updated.getProduct()).isEqualTo("Miel de Romero");
                    assertThat(updated.getPrice()).isEqualTo(new BigDecimal("9.99"));
                });
    }

    @Test
    void testUpdateProductNotFound() {
        Product product = Product.builder()
                .barcode("NOTEXIST123")
                .product("Producto Inexistente")
                .price(new BigDecimal("5.55"))
                .sales(List.of())
                .build();

        webTestClient.put()
                .uri(ProductResource.PRODUCTS + ProductResource.BARCODE_ID, product.getBarcode())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdatePrices() {
        List<ProductPriceUpdating> updates = List.of(
                new ProductPriceUpdating("P001", new BigDecimal("3.33"))
        );

        webTestClient.patch()
                .uri(ProductResource.PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdatePricesNotFound() {
        List<ProductPriceUpdating> updates = List.of(
                new ProductPriceUpdating("0", BigDecimal.ONE)
        );

        webTestClient.patch()
                .uri(ProductResource.PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdatePricesEmptyList() {
        List<ProductPriceUpdating> updates = List.of();

        webTestClient.patch()
                .uri(ProductResource.PRODUCTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();
    }
}