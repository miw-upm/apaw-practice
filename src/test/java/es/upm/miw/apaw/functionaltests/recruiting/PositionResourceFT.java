package es.upm.miw.apaw.functionaltests.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.PositionRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.resources.recruiting.PositionResource;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PositionResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    void setUp() {
        positionRepository.deleteAll();
    }

    @Test
    void testCreatePosition() {
        Position position = Position.builder()
                .reference(1)
                .name("Backend Developer")
                .description("Develop and maintain microservices in Java Spring Boot.")
                .annualSalary(new BigDecimal("40000"))
                .bonusSalary(new BigDecimal("5000"))
                .numVacancies(2)
                .build();

        webTestClient.post()
                .uri(PositionResource.POSITIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(position)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Position.class)
                .value(createdPosition -> {
                    assertThat(createdPosition.getReference()).isEqualTo(1);
                    assertThat(createdPosition.getName()).isEqualTo("Backend Developer");
                    assertThat(createdPosition.getDescription()).contains("microservices");
                    assertThat(createdPosition.getAnnualSalary()).isEqualByComparingTo("40000");
                    assertThat(createdPosition.getBonusSalary()).isEqualByComparingTo("5000");
                    assertThat(createdPosition.getNumVacancies()).isEqualTo(2);
                });

        // Verify persisted entity in MongoDB
        List<PositionEntity> positions = positionRepository.findAll();
        assertThat(positions).hasSize(1);
        assertThat(positions.get(0).getName()).isEqualTo("Backend Developer");
    }

    @Test
    void testCreatePositionBadRequest() {
        // Missing required fields (name and annualSalary)
        Position invalidPosition = Position.builder()
                .reference(2)
                .numVacancies(3)
                .build();

        webTestClient.post()
                .uri(PositionResource.POSITIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidPosition)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
