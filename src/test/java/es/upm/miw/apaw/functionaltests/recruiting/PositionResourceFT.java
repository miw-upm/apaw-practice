package es.upm.miw.apaw.functionaltests.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.PositionRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.resources.recruiting.PositionResource;
import es.upm.miw.apaw.domain.models.recruiting.Position;
import es.upm.miw.apaw.domain.models.recruiting.PositionNumVacanciesUpdating;
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

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void setUp() {
        recruitingSeeder.deleteAll();
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
        assertThat(positions.getFirst().getName()).isEqualTo("Backend Developer");
    }

    @Test
    void testCreatePositionNoReference() {
        Position position = Position.builder()
                .name("SAP HCM Consultant")
                .description("Develop and solution provider in SAP HCM technologies.")
                .annualSalary(new BigDecimal("55000"))
                .bonusSalary(new BigDecimal("9000"))
                .numVacancies(3)
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
                    assertThat(createdPosition.getReference()).isGreaterThan(0);
                    assertThat(createdPosition.getName()).isEqualTo("SAP HCM Consultant");
                    assertThat(createdPosition.getDescription()).contains("SAP");
                    assertThat(createdPosition.getAnnualSalary()).isEqualByComparingTo("55000");
                    assertThat(createdPosition.getBonusSalary()).isEqualByComparingTo("9000");
                    assertThat(createdPosition.getNumVacancies()).isEqualTo(3);
                });

        // Verify persisted entity in MongoDB
        List<PositionEntity> positions = positionRepository.findAll();
        assertThat(positions).hasSize(1);
        assertThat(positions.getFirst().getName()).isEqualTo("SAP HCM Consultant");
    }

    @Test
    void testCreatePositionBadRequest() {
        recruitingSeeder.seedDatabase();

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

    @Test
    void testUpdateNumVacanciesExistingPosition() {
        recruitingSeeder.seedDatabase();

        List<PositionEntity> before = positionRepository.findAll();
        assertThat(before).isNotEmpty();

        PositionEntity abapDeveloper = before.stream()
                .filter(p -> p.getReference().equals(1001))
                .findFirst()
                .orElseThrow();

        assertThat(abapDeveloper.getName()).isEqualTo("ABAP developer");
        assertThat(abapDeveloper.getNumVacancies()).isEqualTo(3);

        // Updating number of vacancies from 3 to 6
        List<PositionNumVacanciesUpdating> updates = List.of(
                PositionNumVacanciesUpdating.builder()
                        .reference(1001)
                        .numVacancies(6)
                        .build()
        );

        webTestClient.patch()
                .uri(PositionResource.POSITIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();

        PositionEntity updated = positionRepository.findById(abapDeveloper.getId()).orElseThrow();

        assertThat(updated.getNumVacancies()).isEqualTo(6);
        assertThat(updated.getName()).isEqualTo("ABAP developer");
    }

    @Test
    void testUpdateMultiplePositionsFromSeeder() {
        recruitingSeeder.seedDatabase();

        List<PositionEntity> positionsBefore = positionRepository.findAll();
        assertThat(positionsBefore).isNotEmpty();

        PositionEntity abapDev = positionsBefore.stream()
                .filter(p -> p.getReference().equals(1001))
                .findFirst()
                .orElseThrow();

        PositionEntity cpiConsultant = positionsBefore.stream()
                .filter(p -> p.getReference().equals(1002))
                .findFirst()
                .orElseThrow();

        assertThat(abapDev.getNumVacancies()).isEqualTo(3);
        assertThat(cpiConsultant.getNumVacancies()).isEqualTo(3);

        List<PositionNumVacanciesUpdating> updates = List.of(
                PositionNumVacanciesUpdating.builder()
                        .reference(1001)
                        .numVacancies(5)
                        .build(),
                PositionNumVacanciesUpdating.builder()
                        .reference(1002)
                        .numVacancies(2)
                        .build()
        );

        webTestClient.patch()
                .uri(PositionResource.POSITIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();

        // --- Assert ---
        PositionEntity updatedAbap = positionRepository.findById(abapDev.getId()).orElseThrow();
        PositionEntity updatedCpi = positionRepository.findById(cpiConsultant.getId()).orElseThrow();

        assertThat(updatedAbap.getNumVacancies()).isEqualTo(5);
        assertThat(updatedCpi.getNumVacancies()).isEqualTo(2);

        assertThat(updatedAbap.getName()).isEqualTo("ABAP developer");
        assertThat(updatedCpi.getName()).isEqualTo("CPI consultant");
    }
}
