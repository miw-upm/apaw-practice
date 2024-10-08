package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import es.upm.miw.apaw_practice.domain.models.university.DegreeUpdate;
import es.upm.miw.apaw_practice.domain.persistence_ports.university.DegreePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.PathMappedEndpoints;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class DegreeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DegreePersistence degreePersistence;
    @Autowired
    private PathMappedEndpoints pathMappedEndpoints;

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
        WebTestClient.ResponseSpec response = queryDegrees("?q=minCapacity:0;maxCapacity:100");
        response.expectStatus().isOk();
        response.expectBodyList(Degree.class);
    }

    @Test
    void testCapacityBetweenSmallRange() {
        WebTestClient.ResponseSpec response = queryDegrees("?q=minCapacity:32;maxCapacity:33");
        response.expectStatus().isOk();
        response.expectBodyList(Degree.class);
    }

    @Test
    void testCapacityBetweenBadRequest() {
        queryDegrees("?q=minCapacity:abc;maxCapacity:100")
                .expectStatus()
                .isBadRequest();
        queryDegrees("?q=minCapacity:0;maxCapacity:abc")
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void testCapacityBetweenParameterMissing() {
        queryDegrees("?q=minCapacity:0")
                .expectStatus()
                .isBadRequest();
        queryDegrees("?q=maxCapacity:100")
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void testCapacityBetweenNoParam() {
        queryDegrees()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void testPatchNonExisting() {
        patchDegrees(List.of(new DegreeUpdate(9999).setCapacity(123)))
                .expectStatus()
                .isNotFound();
    }

    @Test
    void testPatchExisting() {
        Degree degree = degreePersistence.read(2000);
        DegreeUpdate degreeUpdate = new DegreeUpdate(degree.getCode())
                .setCapacity(degree.getCapacity() + 5)
                .setDescription("new description");
        patchDegrees(List.of(degreeUpdate))
                .expectStatus()
                .isOk();
        Degree modifiedDegree = degreePersistence.read(2000);
        assertEquals(degree.getCapacity() + 5, modifiedDegree.getCapacity());
        assertEquals(degreeUpdate.getDescription(), modifiedDegree.getDescription());
        degreePersistence.update(degree.getCode(), degree);
    }

    @Test
    void testPatchMultipleDegrees() {
        Degree degree2000 = degreePersistence.read(2000);
        Degree degree2001 = degreePersistence.read(2001);
        patchDegrees(List.of(
                new DegreeUpdate(degree2000.getCode())
                        .setCapacity(degree2000.getCapacity() + 10)
                        .setKnowledgeArea("new knowledgeArea"),
                new DegreeUpdate(degree2001.getCode())
                        .setDescription("new description")
                        .setKnowledgeArea("Arts")
        )).expectStatus()
                .isOk();
        Degree modifiedDegree2000 = degreePersistence.read(2000);
        assertEquals(degree2000.getCapacity() + 10, modifiedDegree2000.getCapacity());
        assertEquals("new knowledgeArea", modifiedDegree2000.getKnowledgeArea());
        assertEquals(degree2000.getDescription(), modifiedDegree2000.getDescription());
        Degree modifiedDegree2001 = degreePersistence.read(2001);
        assertEquals(degree2001.getCapacity(), modifiedDegree2001.getCapacity());
        assertEquals("Arts", modifiedDegree2001.getKnowledgeArea());
        assertEquals("new description", modifiedDegree2001.getDescription());
        degreePersistence.update(degree2000.getCode(), degree2000);
        degreePersistence.update(degree2001.getCode(), degree2001);
    }

    private WebTestClient.ResponseSpec queryDegrees() {
        return queryDegrees("");
    }

    private WebTestClient.ResponseSpec queryDegrees(String queryParams) {
        return webTestClient
                .get()
                .uri(DegreeResource.DEGREES + DegreeResource.SEARCH + queryParams)
                .exchange();
    }

    private WebTestClient.ResponseSpec patchDegrees(List<DegreeUpdate> degreeUpdateList) {
        return webTestClient
                .patch()
                .uri(DegreeResource.DEGREES)
                .body(BodyInserters.fromValue(degreeUpdateList))
                .exchange();
    }
}
