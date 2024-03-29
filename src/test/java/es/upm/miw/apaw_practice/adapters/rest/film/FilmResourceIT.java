package es.upm.miw.apaw_practice.adapters.rest.film;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.film.Film;
import es.upm.miw.apaw_practice.domain.models.film.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.film.FilmResource.*;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class FilmResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(FILMS + TITLE_ID, "Gladiator")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Film.class)
                .consumeWith(films -> {
                    assertNotNull(films.getResponseBody());
                    Film film = films.getResponseBody().get(0);
                    assertEquals("Synopsis 3", film.getSynopsis());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(FILMS + TITLE_ID, "None")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateReviews() {
        Review review1 = new Review(8, "New comment 1", true);
        Review review2 = new Review(10, "New comment 2", true);
        List<Review> reviews = Arrays.asList(review1, review2);
        this.webTestClient
                .put()
                .uri(FILMS + TITLE_ID + REVIEWS, "Face/Off")
                .body(BodyInserters.fromValue(reviews))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Film.class)
                .value(Assertions::assertNotNull)
                .value(film -> {
                    assertEquals(2, film.getReviews().size());
                    assertEquals(8, film.getReviews().get(0).getRating());
                    assertEquals("New comment 1", film.getReviews().get(0).getComment());
                    assertTrue(film.getReviews().get(0).getRecommendation());
                });
    }

    @Test
    void testUpdateReviewsNotFound() {
        List<Review> reviews = Arrays.asList(new Review(2, "New comment 1", false));
        this.webTestClient
                .put()
                .uri(FILMS + TITLE_ID + REVIEWS, "Not Found")
                .body(BodyInserters.fromValue(reviews))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindAverageRatingByDirectorDni() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FILMS + SEARCH_RATING)
                                .queryParam("q", "dni:05645800X")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Double.class)
                .value(Assertions::assertNotNull)
                .value(average -> assertEquals(8.0, average));
    }

    @Test
    void testBadRequestFindAverageRatingByDirectorDni() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FILMS + SEARCH_RATING)
                                .queryParam("q", "id:05645800X")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testZeroFindAverageRatingByDirectorDni() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FILMS + SEARCH_RATING)
                                .queryParam("q", "dni:00")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Double.class)
                .value(Assertions::assertNotNull)
                .value(average -> assertEquals(0.0, average));
    }

    @Test
    void testFindCommentsWithTrueRecommendationByGenreStyle() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FILMS + SEARCH_COMMENT)
                                .queryParam("q", "style:Surrealist")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .consumeWith(comments -> {
                    assertNotNull(comments.getResponseBody());
                    assertTrue(comments.getResponseBody().size() > 0);
                    assertEquals("[\"Comment 7\"]", comments.getResponseBody().get(0));
                });
    }

    @Test
    void testBadRequestFindCommentsWithTrueRecommendationByGenreStyle() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FILMS + SEARCH_COMMENT)
                                .queryParam("q", "name:Surrealist")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testNotFoundCommentsWithTrueRecommendationByGenreStyle() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FILMS + SEARCH_COMMENT)
                                .queryParam("q", "style:No_exist")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }
}
