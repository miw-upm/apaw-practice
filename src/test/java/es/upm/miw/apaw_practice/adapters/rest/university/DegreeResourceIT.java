package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class DegreeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.CODE_ID, "2001")
                .exchange();
        response.expectStatus().isOk();
        response.expectBody(Degree.class);
        response.returnResult(Degree.class).getResponseBody().subscribe(degree -> {
            assertEquals(2001, degree.getCode());
            assertEquals(40, degree.getCapacity());
            assertEquals("Visual Arts", degree.getKnowledgeArea());
            assertEquals("Training focused on the analysis and creation of contemporary works, combining theory and practice in disciplines such as photography, video and graphic design.", degree.getDescription());
        });
    }

    @Test
    void testReadNotFound() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.CODE_ID, "9999")
                .exchange();
        response.expectStatus().isNotFound();
    }

    @Test
    void testCapacityBetween() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH + "?q=minCapacity:0;maxCapacity:100")
                .exchange();
        response.expectStatus().isOk();
        response.expectBodyList(Degree.class);
    }

    @Test
    void testCapacityBetweenSmallRange() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH + "?q=minCapacity:32;maxCapacity:33")
                .exchange();
        response.expectStatus().isOk();
        response.expectBodyList(Degree.class);
    }

    @Test
    void testCapacityBetweenBadRequest() {
        webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH + "?q=minCapacity:abc;maxCapacity:100")
                .exchange()
                .expectStatus()
                .isBadRequest();
        webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH + "?q=minCapacity:0;maxCapacity:abc")
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void testCapacityBetweenParameterMissing() {
        webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH + "?q=minCapacity:0")
                .exchange()
                .expectStatus()
                .isBadRequest();
        webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH + "?q=maxCapacity:100")
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void testCapacityBetweenNoParam() {
        webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}
