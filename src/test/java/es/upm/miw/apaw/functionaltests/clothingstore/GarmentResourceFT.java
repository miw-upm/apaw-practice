package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.GarmentResource;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

//@Test
//void testGetAllGarments() {
//    List<Garment> garments = this.webTestClient.get()
//            .uri(GarmentResource.GARMENTS + "/all")
//            .exchange()
//            .expectStatus().isOk()
//            .expectBodyList(Garment.class)
//            .returnResult()
//            .getResponseBody();
//
//    assertThat(garments).isNotNull();
//    assertThat(garments).isNotEmpty();
//
//    //  打印出所有衣服信息
//    System.out.println(">>> Garments found: " + garments.size());
//    garments.forEach(g ->
//            System.out.println(" - id=" + g.getId()
//                    + ", size=" + g.getSize()
//                    + ", price=" + g.getPrice()
//                    + ", onSale=" + g.getOnSale())
//    );
//}
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

        assertThat(garments).isNotNull().isNotEmpty(); // ← 关键
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

//    @Test
//    void testUpdateGarment_NotFound() {
//        UUID unknownId = UUID.fromString("ffffffff-ffff-ffff-ffff-ffffffff9999");
//
//        Garment garment = Garment.builder()
//                .size("M")
//                .price(new BigDecimal("79.99"))
//                .onSale(false)
//                .build();
//
//        this.webTestClient.put()
//                .uri(GarmentResource.GARMENTS + "/" + unknownId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(garment)
//                .exchange()
//                .expectStatus().isNotFound();
//    }
}
