package es.upm.miw.apaw.functionaltests.vehicle;

import es.upm.miw.apaw.adapters.resources.vehicle.EngineResource;
import es.upm.miw.apaw.domain.models.vehicle.Engine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class EngineResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Engine engine = Engine.builder()
                .codeEngine("VMIVDS000VIS00010")
                .type("Hybrid")
                .displacement(1600.0)
                .build();

        webTestClient.post()
                .uri(EngineResource.ENGINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(engine)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Engine.class)
                .value(created -> {
                    assertThat(created).isNotNull();
                    assertThat(created.getCodeEngine()).isEqualTo("VMIVDS000VIS00010");
                });
    }

    @Test
    void testCreateConflict() {
        Engine engine = Engine.builder()
                .codeEngine("VMIVDS000VIS00000")
                .type("Diesel")
                .displacement(1600.0)
                .build();

        webTestClient.post()
                .uri(EngineResource.ENGINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(engine)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateBadRequest() {
        Engine engine = Engine.builder()
                .type("Gasolina")
                .displacement(2000.0)
                .build();

        webTestClient.post()
                .uri(EngineResource.ENGINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(engine)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdate() {
        Engine engine = Engine.builder()
                .codeEngine("VMIVDS000VIS00000")
                .type("Diesel Updated")
                .displacement(1800.0)
                .build();

        webTestClient.put()
                .uri(EngineResource.ENGINES + EngineResource.CODE_ENGINE_ID, "VMIVDS000VIS00000")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(engine)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Engine.class)
                .value(updated -> {
                    assertThat(updated.getCodeEngine()).isEqualTo("VMIVDS000VIS00000");
                    assertThat(updated.getType()).contains("Updated");
                });
    }

    @Test
    void testUpdateConflict() {
        Engine engine = Engine.builder()
                .codeEngine("OtherCode")
                .type("Diesel")
                .displacement(1800.0)
                .build();

        webTestClient.put()
                .uri(EngineResource.ENGINES + EngineResource.CODE_ENGINE_ID, "VMIVDS000VIS00000")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(engine)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
