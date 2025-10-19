package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.GarmentResource;
import es.upm.miw.apaw.domain.exceptions.BadGatewayException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class GarmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    @MockitoBean
    private UserRestClient userRestClient;

    private static final String SUM_PRICE_SEARCH_PATH = GarmentResource.GARMENTS + "/search/sum-price";
    private static final String DISTINCT_IDS_SEARCH_PATH = GarmentResource.GARMENTS + "/search/distinct-ids";

    private static final String KNOWN_MOBILE   = "666000660";
    private static final String UNKNOWN_MOBILE = "999999999";
    private static final UUID   SEEDED_USER_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");

    // 与 clothingstoreSeeder 一致
    private static final String KNOWN_INVOICE_NUMBER = "INV-2025-001";
    private static final UUID   G1_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
    private static final UUID   G2_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002");

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();

        UserDto mockUser = UserDto.builder()
                .id(SEEDED_USER_ID)
                .mobile(KNOWN_MOBILE)
                .firstName("user0")
                .build();
        given(userRestClient.readByMobile(KNOWN_MOBILE)).willReturn(mockUser);

        given(userRestClient.readByMobile(UNKNOWN_MOBILE))
                .willThrow(new BadGatewayException("User not found with MOBILE: " + UNKNOWN_MOBILE));
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
        if (garments.isEmpty()) return;

        UUID id = garments.get(0).getId();

        Garment body = Garment.builder()
                .size("XL")
                .price(new BigDecimal("129.99"))
                .onSale(true)
                .build();

        Garment updated = this.webTestClient.put()
                .uri(GarmentResource.GARMENTS + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
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
    void testCreate() {
        Garment body = Garment.builder()
                .size("S").price(new BigDecimal("19.99")).onSale(false).build();

        Garment created = this.webTestClient.post()
                .uri(GarmentResource.GARMENTS)
                .contentType(MediaType.APPLICATION_JSON)
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
                .value(list -> assertThat(list).noneMatch(g -> g.getId().equals(idToDelete)));
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
        assertThat(total).isEqualByComparingTo("149.98"); // 59.99 + 89.99
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

    @Test
    void testFindDistinctIdsByInvoiceNumber_ok() {
        List<UUID> ids = this.webTestClient.get()
                .uri(uri -> uri.path(DISTINCT_IDS_SEARCH_PATH)
                        .queryParam("number", KNOWN_INVOICE_NUMBER) // 参数名必须是 number
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UUID.class)
                .returnResult()
                .getResponseBody();

        assertThat(ids).isNotNull();
        assertThat(Set.copyOf(ids))
                .containsExactlyInAnyOrder(G1_ID, G2_ID);
    }
}



