package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class BookResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindAll() {
        this.webTestClient
                .get()
                .uri(BookResource.BOOKS)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void testFindCategoryNameByAuthorFullName(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(BookResource.BOOKS + BookResource.FULLNAME)
                                .queryParam("fullname", "Alda do Espírito Santo")
                                .build())
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void testFindTop1AuthorNationalityByDescriptionCategory(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(BookResource.BOOKS + BookResource.DESCRIPTION)
                                .queryParam("desc", "a conflict that takes place in the lives of character")
                                .build())
                .exchange()
                .expectStatus().isOk();
    }
}
