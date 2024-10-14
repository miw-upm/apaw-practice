package es.upm.miw.apaw_practice.adapters.rest.music_lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Branch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class BranchResourceIT {

  private final List<String> madridBranchCodes = List.of("MAD-PRDO", "MAD-SBCT");

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void testSearchByAddress() {
    this.webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder.path(BranchResource.BRANCHES + BranchResource.SEARCH)
                .queryParam("q", "address:Madrid")
                .build())
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Branch.class)
        .value(branches -> assertFalse(branches.isEmpty()))
        .value(articles -> assertTrue(articles.stream()
            .map(Branch::getCode)
            .allMatch(madridBranchCodes::contains)));
  }

}
