
package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.GarmentResource;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class GarmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    private static final String SUM_PRICE_SEARCH_PATH = GarmentResource.GARMENTS + "/search/sum-price";
    private static final String KNOWN_MOBILE = "666000660";
    private static final String UNKNOWN_MOBILE = "999999999";


    @BeforeEach
    void seed() {
        databaseSeeder.reSeedDatabase();
        System.out.println(">>> After reseed, GET size = " +
                webTestClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path(GarmentResource.GARMENTS)
                                .queryParam("min", "0")
                                .queryParam("max", "100000")
                                .build())
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(Garment.class)
                        .returnResult()
                        .getResponseBody()
                        .size()
        );
    }

    @Test
    void testFindByPriceBetween() {
        List<Garment> garments = this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(GarmentResource.GARMENTS)
                        .queryParam("min", "50")
                        .queryParam("max", "100")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Garment.class)
                .returnResult()
                .getResponseBody();

        assertThat(garments).isNotNull().isNotEmpty();
        assertThat(garments).allSatisfy(g ->
                assertThat(g.getPrice()).isBetween(new BigDecimal("50"), new BigDecimal("100"))
        );
    }

    @Test
    void testUpdateGarment_Ok() {
        List<Garment> garments = this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(GarmentResource.GARMENTS)
                        .queryParam("min", "0")
                        .queryParam("max", "100000")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Garment.class)
                .returnResult()
                .getResponseBody();

        assertThat(garments).isNotNull();
        if (garments.isEmpty()) {
            System.out.println(" No Garment data found in the current database; skipping PUT test");
            return;
        }

        UUID id = garments.get(0).getId();

        Garment body = Garment.builder()
                .size("XL")
                .price(new BigDecimal("129.99"))
                .onSale(true)
                .build();

        Garment updated = this.webTestClient.put()
                .uri(GarmentResource.GARMENTS + "/" + id)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Garment.class)
                .returnResult()
                .getResponseBody();

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(id);
        assertThat(updated.getSize()).isEqualTo("XL");
        assertThat(updated.getPrice()).isEqualByComparingTo("129.99");
        assertThat(updated.getOnSale()).isTrue();
    }

    @Test
    void testCreate(){
        Garment body = Garment.builder()
                .size("S").price(new BigDecimal("19.99")).onSale(false).build();

        Garment created = this.webTestClient.post()
                .uri(GarmentResource.GARMENTS)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Garment.class)
                .returnResult()
                .getResponseBody();

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getSize()).isEqualTo("S");
        assertThat(created.getPrice()).isEqualByComparingTo("19.99");
        assertThat(created.getOnSale()).isFalse();

        List<Garment> query = this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(GarmentResource.GARMENTS)
                        .queryParam("min","0").queryParam("max","20").build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Garment.class)
                .returnResult()
                .getResponseBody();

        assertThat(query).isNotNull();
        assertThat(query.stream().anyMatch(g -> g.getId().equals(created.getId()))).isTrue();
    }

    @Test
    void testDeleteGarment_Ok() {
        List<Garment> garments = this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(GarmentResource.GARMENTS)
                        .queryParam("min", "0")
                        .queryParam("max", "100000")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Garment.class)
                .returnResult()
                .getResponseBody();

        assertThat(garments).isNotEmpty();
        UUID idToDelete = garments.get(0).getId();

        this.webTestClient.delete()
                .uri(GarmentResource.GARMENTS + "/" + idToDelete)
                .exchange()
                .expectStatus().isNoContent();

        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(GarmentResource.GARMENTS)
                        .queryParam("min", "0")
                        .queryParam("max", "100000")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Garment.class)
                .value(list ->
                        assertThat(list)
                                .noneMatch(g -> g.getId().equals(idToDelete))
                );
    }


    @Test
    void testSumDistinctPriceByMobile_ok() {
        BigDecimal total = this.webTestClient.get()
                .uri(uri -> uri.path(SUM_PRICE_SEARCH_PATH)
                        .queryParam("mobile", KNOWN_MOBILE)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .returnResult()
                .getResponseBody();

        assertThat(total).isNotNull();
        assertThat(total).isGreaterThanOrEqualTo(BigDecimal.ZERO);
        System.out.println(">>> sumDistinctPrice(" + KNOWN_MOBILE + ") = " + total);
    }
    @Test
    void testSumDistinctPriceByMobile_userNotFound() {
        this.webTestClient.get()
                .uri(uri -> uri.path(SUM_PRICE_SEARCH_PATH)
                        .queryParam("mobile", UNKNOWN_MOBILE)
                        .build())
                .exchange()
                .expectStatus().isEqualTo(502);
    }
}
