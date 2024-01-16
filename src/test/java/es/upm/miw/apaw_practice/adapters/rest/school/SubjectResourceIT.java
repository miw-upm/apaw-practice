package es.upm.miw.apaw_practice.adapters.rest.school;

import es.upm.miw.apaw_practice.adapters.mongodb.school.SchoolSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.school.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class SubjectResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SchoolSeederService schoolSeederService;

    @AfterEach
    void reSeedDatabase () {
        this.schoolSeederService.deleteAll();
        this.schoolSeederService.seedDatabase();
    }
    @Test
    void testCreate() {
        Subject subject =
                new Subject("subjectTest", "descriptiontest", true, 50);
        this.webTestClient
                .post()
                .uri(SubjectResource.SUBJECTS)
                .body(BodyInserters.fromValue(subject))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Subject.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        Subject subject =
                new Subject("subject5", "foo", true, 50);
        this.webTestClient
                .post()
                .uri(SubjectResource.SUBJECTS)
                .body(BodyInserters.fromValue(subject))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testReadByTitle() {
        this.webTestClient
                .get()
                .uri(SubjectResource.SUBJECTS + SubjectResource.TITLE_ID, "subject4")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Subject.class)
                .value(Assertions::assertNotNull)
                .value(subjectData -> {
                    assertEquals("subject4", subjectData.getTitle());
                    assertEquals(1, subjectData.getCredits());
                    assertFalse(subjectData.getBilingual());
                });
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(SubjectResource.SUBJECTS + SubjectResource.TITLE_ID, "subjectResource")
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void testFindUniqueDescriptionBySmartBoard() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(SubjectResource.SUBJECTS + SubjectResource.SEARCH_DESCRIPTION_BY_SMARTBOARD)
                                .queryParam("q", "smartboard:true")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(descriptions -> assertEquals(List.of("desc1", "desc2", "desc3", "desc4", "desc5", "descSearch2", "descSearch3"),
                             descriptions));

        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(SubjectResource.SUBJECTS + SubjectResource.SEARCH_DESCRIPTION_BY_SMARTBOARD)
                                .queryParam("q", "smartboard:false")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(descriptions -> assertEquals(List.of("desc2", "descSearch1", "descSearch3"),
                             descriptions));
    }

    @Test
    void testFindUniqueDescriptionBySmartBoardBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(SubjectResource.SUBJECTS + SubjectResource.SEARCH_DESCRIPTION_BY_SMARTBOARD)
                                .queryParam("q", "foo")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testFindUniqueDescriptionByEmail() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(SubjectResource.SUBJECTS + SubjectResource.SEARCH_DESCRIPTION_BY_EMAIL)
                                .queryParam("q", "email:student6@mail.test")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(descriptions -> assertEquals(List.of("desc2", "desc4", "descSearch3"),
                        descriptions));

        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(SubjectResource.SUBJECTS + SubjectResource.SEARCH_DESCRIPTION_BY_EMAIL)
                                .queryParam("q", "email:student2@mail.test")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(descriptions -> assertEquals(List.of(), descriptions));
    }

    @Test
    void testFindUniqueDescriptionByEmailBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(SubjectResource.SUBJECTS + SubjectResource.SEARCH_DESCRIPTION_BY_EMAIL)
                                .queryParam("q", "foo")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}
