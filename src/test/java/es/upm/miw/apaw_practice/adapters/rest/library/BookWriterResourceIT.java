package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.library.dto.BookWriterCollectionDto;
import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RestTestConfig
class BookWriterResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        BookWriter bookWriter =
                new BookWriter("Antonio Hidalgo","A. Hidalgo",1);
        this.webTestClient
                .post()
                .uri(BookWriterResource.BOOKWRITER)
                .body(BodyInserters.fromValue(bookWriter))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookWriter.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict(){
        BookWriter bookWriter =
                new BookWriter("Echo", "San Mao", null);
        this.webTestClient
                .post()
                .uri(BookWriterResource.BOOKWRITER)
                .body(BodyInserters.fromValue(bookWriter))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateNumberOfBook(){
        this.webTestClient
                .put()
                .uri(BookWriterResource.BOOKWRITER + BookWriterResource.NICKNAME_ID + BookWriterResource.NUMBER_OF_BOOK, "Cixin")
                .body(BodyInserters.fromValue(30))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookWriter.class)
                .value(Assertions:: assertNotNull)
                .value(bookWriter -> {
                    assertEquals("Cixin Liu", bookWriter.getName());
                    assertEquals("Cixin", bookWriter.getNickname());
                    assertEquals(30, bookWriter.getNumberOfBook());
                });
    }

    @Test
    void testFindAverageOfNumberOfBookByLibraryName() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(BookWriterResource.BOOKWRITER+ BookWriterResource.SEARCH)
                                .queryParam("q", "name:Biblioteca territorial")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Double.class)
                .value(Assertions::assertNotNull)
                .value(averageOfNumberOfBook ->
                        assertEquals(BigDecimal.valueOf(2).setScale(2,RoundingMode.HALF_UP), BigDecimal.valueOf(averageOfNumberOfBook).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    void testFindNamesOfBookWritersByIsbn(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(BookWriterResource.BOOKWRITER + BookWriterResource.SEARCH2)
                                .queryParam("q","isbn:9788888888888")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookWriterCollectionDto.class)
                .value(Assertions::assertNotNull)
                .value(bookWriterName -> {
                    assertEquals(1, bookWriterName.getNamesOfBookWriter().size());
                    assertNotNull(bookWriterName.getNamesOfBookWriter().get(0));
                    assertEquals("Autor", bookWriterName.getNamesOfBookWriter().get(0));
                });
    }
}
