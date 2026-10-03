package es.upm.miw.apaw.functionaltests.immigrationissues;

import es.upm.miw.apaw.adapters.in.immigrationissues.ImmigrationIssueResource;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.immigrationissues.CreationImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ImmigrationIssuesSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.ImmigrationIssuesSeederForDev.ID_1;
import static es.upm.miw.apaw.config.seeders.ImmigrationIssuesSeederForDev.LAW_BASIS_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ImmigrationIssueResourceFT {

    private static final String APAW_USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    private static final UUID APAW_USER_ID_0 = UUID.fromString(APAW_USER_PREFIX + "0000");

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @MockitoBean
    private UserFinder userFinder;

    private UserSnapshot user;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
        this.user = UserSnapshot.builder()
                .id(APAW_USER_ID_0)
                .mobile("600000100")
                .firstName("cliente0")
                .familyName("García López")
                .email("cliente0@example.com")
                .build();
        when(this.userFinder.read(this.user.getId())).thenReturn(this.user);
    }

    @Test
    void testCreate() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0, ID_1));

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ImmigrationIssue.class)
                .value(issue -> {
                    assertThat(issue).isNotNull();
                    assertThat(issue.getId()).isNotNull();
                    assertThat(issue.getSubject()).isEqualTo(creation.getSubject());
                    assertThat(issue.getClientNationality()).isEqualTo(creation.getClientNationality());
                    assertThat(issue.getClientImmigrationStatus())
                            .isEqualTo(creation.getClientImmigrationStatus());
                    assertThat(issue.getResponseDueDate()).isEqualTo(creation.getResponseDueDate());
                    assertThat(issue.getOpenedAt()).isNotNull();
                    assertThat(issue.getLawBases()).extracting(LawBasis::getId).containsExactly(ID_0, ID_1);
                    assertThat(issue.getLawBases()).extracting(LawBasis::getLawCode)
                            .containsExactly(LAW_BASIS_0.getLawCode(), "ES-LB-002");
                    assertThat(issue.getUserSnapshot()).isEqualTo(this.user);
                });
    }

    @Test
    void testCreateAppliesDefaultEstimatedCost() {
        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(this.creation(List.of(ID_0)))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ImmigrationIssue.class)
                .value(issue -> assertThat(issue.getEstimatedCost()).isEqualByComparingTo(BigDecimal.ZERO));
    }

    @Test
    void testCreateKeepsProvidedEstimatedCost() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setEstimatedCost(new BigDecimal("725.50"));

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ImmigrationIssue.class)
                .value(issue -> assertThat(issue.getEstimatedCost())
                        .isEqualByComparingTo(new BigDecimal("725.50")));
    }

    @Test
    void testCreateBlankSubject() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setSubject(" ");

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBlankClientNationality() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setClientNationality(" ");

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingResponseDueDate() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setResponseDueDate(null);

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateEmptyLawBasisIds() {
        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(this.creation(List.of()))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingLawBasisIdInList() {
        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(this.creation(Arrays.asList(ID_0, null)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingUserId() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setUserId(null);

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateLawBasisNotFound() {
        UUID unknownLawBasisId = UUID.randomUUID();
        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(this.creation(List.of(ID_0, unknownLawBasisId)))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message"))
                        .contains(unknownLawBasisId.toString()));
    }

    @Test
    void testCreateUserNotFound() {
        UUID unknownUserId = UUID.fromString(APAW_USER_PREFIX + "0009");
        when(this.userFinder.read(unknownUserId))
                .thenThrow(new NotFoundException("Not found on read user by id " + unknownUserId));
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setUserId(unknownUserId);

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(unknownUserId.toString()));
    }

    @Test
    void testCreateDuplicateSubject() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        this.create(creation);
        CreationImmigrationIssue repeated = this.creation(List.of(ID_1));
        repeated.setSubject(creation.getSubject());

        this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(repeated)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT)
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message"))
                        .contains(creation.getSubject()));
    }

    private CreationImmigrationIssue creation(List<UUID> lawBasisIds) {
        return CreationImmigrationIssue.builder()
                .subject("Immigration issue " + UUID.randomUUID())
                .clientNationality("Colombia")
                .clientImmigrationStatus("Permiso en vigor")
                .responseDueDate(LocalDate.of(2026, 6, 1))
                .lawBasisIds(lawBasisIds)
                .userId(this.user.getId())
                .build();
    }

    private ImmigrationIssue create(CreationImmigrationIssue creation) {
        return this.restTestClient.post().uri(ImmigrationIssueResource.IMMIGRATION_ISSUES)
                .body(creation)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ImmigrationIssue.class).returnResult().getResponseBody();
    }
}