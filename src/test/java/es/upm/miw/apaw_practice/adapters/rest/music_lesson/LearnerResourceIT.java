package es.upm.miw.apaw_practice.adapters.rest.music_lesson;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class LearnerResourceIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void testDelete() {
    this.webTestClient
        .delete()
        .uri(LearnerResource.LEARNERS + LearnerResource.IDENTITY_DOCUMENT_ID, "O4758589")
        .exchange()
        .expectStatus().isOk();
  }

}
