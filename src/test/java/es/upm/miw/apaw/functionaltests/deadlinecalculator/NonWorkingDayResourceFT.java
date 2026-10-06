package es.upm.miw.apaw.functionaltests.deadlinecalculator;

import es.upm.miw.apaw.adapters.in.deadlinecalculator.NonWorkingDayResource;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDay;
import es.upm.miw.apaw.domain.model.deadlinecalculator.NonWorkingDayRecurringUpdate;
import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.DeadlineCalculatorSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class NonWorkingDayResourceFT {
    private static final String MADRID = "Madrid";
    private static final String MESSAGE = "message";

    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    private NonWorkingDay.NonWorkingDayBuilder national(int month, int day) {
        return NonWorkingDay.builder()
                .date(LocalDate.of(2030, month, day))
                .description("Festivo de prueba " + month + "-" + day)
                .scopeLevel(ScopeLevel.NATIONAL);
    }

    private NonWorkingDay create(NonWorkingDay nonWorkingDay) {
        return this.restTestClient.post().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(nonWorkingDay)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NonWorkingDay.class)
                .returnResult().getResponseBody();
    }

    // ---------- POST ----------

    @Test
    void testCreate() {
        this.restTestClient.post().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(this.national(1, 2).build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NonWorkingDay.class)
                .value(body -> {
                    assertThat(body.getId()).isNotNull();
                    assertThat(body).usingRecursiveComparison().ignoringFields("id")
                            .isEqualTo(this.national(1, 2).recurring(false).build());
                });
    }

    @Test
    void testCreateIgnoresBodyId() {
        UUID bodyId = UUID.randomUUID();
        this.restTestClient.post().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(this.national(1, 3).id(bodyId).build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NonWorkingDay.class)
                .value(body -> assertThat(body.getId()).isNotEqualTo(bodyId));
    }

    @Test
    void testCreateDuplicateConflict() {
        this.restTestClient.post().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(NonWorkingDay.builder()
                        .date(NON_WORKING_DAY_0.getDate())
                        .description("Otro nombre para el mismo día")
                        .scopeLevel(ScopeLevel.NATIONAL)
                        .build())
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get(MESSAGE)).contains("already exists"));
    }

    @Test
    void testCreateInconsistentScopeBadRequest() {
        this.restTestClient.post().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(this.national(1, 4).region(MADRID).build())
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get(MESSAGE))
                        .contains("Invalid scope for non working day"));
    }

    @Test
    void testCreateWithoutDescriptionBadRequest() {
        this.restTestClient.post().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(Map.of("date", "2030-01-05", "scopeLevel", "NATIONAL"))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get(MESSAGE)).contains("description"));
    }

    // ---------- GET /{id} ----------

    @Test
    void testRead() {
        this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + ID_5)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NonWorkingDay.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(NON_WORKING_DAY_5));
    }

    @Test
    void testReadNationalReturnsNullRegionAndCity() {
        this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NonWorkingDay.class)
                .value(body -> {
                    assertThat(body.getRegion()).isNull();
                    assertThat(body.getCity()).isNull();
                });
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get(MESSAGE)).contains(id.toString()));
    }

    @Test
    void testReadWithAnInvalidIdBadRequest() {
        this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/not-a-uuid")
                .exchange()
                .expectStatus().isBadRequest();
    }

    // ---------- GET ----------

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NonWorkingDay[].class)
                .value(body -> assertThat(body).extracting(NonWorkingDay::getId)
                        .contains(ID_0, ID_5, ID_6, ID_10));
    }

    @Test
    void testFindAllKeepsTheSameOrder() {
        List<UUID> first = this.findAllIds();
        List<UUID> second = this.findAllIds();
        assertThat(first).isEqualTo(second);
        assertThat(first).containsSubsequence(ID_5, ID_6);
    }

    private List<UUID> findAllIds() {
        NonWorkingDay[] body = this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NonWorkingDay[].class)
                .returnResult().getResponseBody();
        return Arrays.stream(body).map(NonWorkingDay::getId).toList();
    }

    // ---------- PUT ----------

    @Test
    void testUpdate() {
        NonWorkingDay created = this.create(this.national(4, 1).build());
        created.setDescription("Nombre corregido");
        this.restTestClient.put().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + created.getId())
                .body(created)
                .exchange()
                .expectStatus().isOk()
                .expectBody(NonWorkingDay.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(created));
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + UUID.randomUUID())
                .body(this.national(4, 2).build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateReferencedDateConflict() {
        NonWorkingDay referenced = NonWorkingDay.builder()
                .date(LocalDate.of(2030, 4, 3))
                .description(NON_WORKING_DAY_10.getDescription())
                .scopeLevel(NON_WORKING_DAY_10.getScopeLevel())
                .region(NON_WORKING_DAY_10.getRegion())
                .city(NON_WORKING_DAY_10.getCity())
                .recurring(NON_WORKING_DAY_10.getRecurring())
                .build();
        this.restTestClient.put().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + ID_10)
                .body(referenced)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get(MESSAGE)).contains("used by a deadline"));
    }

    @Test
    void testUpdateInconsistentScopeBadRequest() {
        NonWorkingDay created = this.create(this.national(4, 4).build());
        created.setRegion(MADRID);
        this.restTestClient.put().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + created.getId())
                .body(created)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get(MESSAGE))
                        .contains("Invalid scope for non working day"));
    }

    // ---------- PATCH ----------

    @Test
    void testUpdateRecurrences() {
        NonWorkingDay first = this.create(this.national(6, 1).build());
        NonWorkingDay second = this.create(this.national(6, 2).build());
        this.restTestClient.patch().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(List.of(
                        new NonWorkingDayRecurringUpdate(first.getId(), true),
                        new NonWorkingDayRecurringUpdate(second.getId(), true)))
                .exchange()
                .expectStatus().isOk();
        this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + first.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(NonWorkingDay.class)
                .value(body -> assertThat(body.getRecurring()).isTrue());
    }

    @Test
    void testUpdateRecurrencesWithAnEmptyListBadRequest() {
        this.restTestClient.patch().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(List.of())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateRecurrencesWithAnUnknownIdNotFound() {
        this.restTestClient.patch().uri(NonWorkingDayResource.NON_WORKING_DAYS)
                .body(List.of(new NonWorkingDayRecurringUpdate(UUID.randomUUID(), true)))
                .exchange()
                .expectStatus().isNotFound();
    }

    // ---------- DELETE ----------

    @Test
    void testDelete() {
        NonWorkingDay created = this.create(this.national(8, 1).build());
        this.restTestClient.delete().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + created.getId())
                .exchange()
                .expectStatus().isNoContent();
        this.restTestClient.get().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + created.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteReferencedConflict() {
        this.restTestClient.delete().uri(NonWorkingDayResource.NON_WORKING_DAYS + "/" + ID_10)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get(MESSAGE)).contains("referenced by a deadline"));
    }
}
