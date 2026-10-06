package es.upm.miw.apaw.functionaltests.stucktaskdetector;

import es.upm.miw.apaw.adapters.in.stucktaskdetector.StuckTaskRuleResource;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRule;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleAlertReport;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskRuleCreation;
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
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.StuckTaskDetectorSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StuckTaskRuleResourceFT {
    private static final String RULES = StuckTaskRuleResource.STUCK_TASK_RULES;
    private static final String REPORT = RULES + StuckTaskRuleResource.REPORT;
    private static final UserSnapshot USER = UserSnapshot.builder()
            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
            .mobile("600000100")
            .firstName("cliente0")
            .build();

    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testCreate() {
        StuckTaskRuleCreation creation = this.newCreation();
        when(this.userFinder.read(USER.getId())).thenReturn(USER);

        this.restTestClient.post().uri(RULES)
                .body(creation)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StuckTaskRule.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getName()).isEqualTo(creation.getName());
                    assertThat(body.getProcedureKeyword()).isEqualTo("labour");
                    assertThat(body.getThresholdDays()).isEqualTo(20);
                    assertThat(body.getPenaltyAmount()).isEqualByComparingTo("75.50");
                    assertThat(body.getActive()).isTrue();
                    assertThat(body.getCreatedAt()).isEqualTo(LocalDate.now());
                    assertThat(body.getCreatedByUser().getId()).isEqualTo(USER.getId());
                    assertThat(body.getCreatedByUser().getMobile()).isEqualTo("600000100");
                });
    }

    @Test
    void testCreateDuplicateName() {
        StuckTaskRuleCreation creation = this.newCreation();
        creation.setName(RULE_0.getName());
        this.restTestClient.post().uri(RULES)
                .body(creation)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateUserNotFound() {
        when(this.userFinder.read(USER.getId()))
                .thenThrow(new NotFoundException("User id not found: " + USER.getId()));
        this.restTestClient.post().uri(RULES)
                .body(this.newCreation())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateBlankName() {
        StuckTaskRuleCreation creation = this.newCreation();
        creation.setName(" ");
        this.restTestClient.post().uri(RULES)
                .body(creation)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateWithoutUser() {
        StuckTaskRuleCreation creation = this.newCreation();
        creation.setUserId(null);
        this.restTestClient.post().uri(RULES)
                .body(creation)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testFindAlertReport() {
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(id -> UserSnapshot.builder().id(id).mobile("600000100").build()).toList();
        });

        this.restTestClient.get().uri(REPORT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskRuleAlertReport[].class).value(body -> {
                    assertThat(body).extracting(StuckTaskRuleAlertReport::getRuleName)
                            .contains(RULE_0.getName(), RULE_1.getName(), RULE_2.getName());
                    assertThat(body).extracting(StuckTaskRuleAlertReport::getTotalAlertCount)
                            .isSortedAccordingTo(Comparator.reverseOrder());
                    assertThat(body).filteredOn(item -> item.getRuleName().equals(RULE_0.getName()))
                            .singleElement().satisfies(item -> {
                                assertThat(item.getTotalAlertCount()).isGreaterThanOrEqualTo(2);
                                assertThat(item.getUnresolvedAlertCount()).isGreaterThanOrEqualTo(1);
                                assertThat(item.getCreatedByUser().getId())
                                        .isEqualTo(RULE_0.getCreatedByUser().getId());
                                assertThat(item.getCreatedByUser().getMobile()).isEqualTo("600000100");
                            });
                });
    }

    @Test
    void testFindAlertReportUserNotFound() {
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of());
        this.restTestClient.get().uri(REPORT)
                .exchange()
                .expectStatus().isNotFound();
    }

    private StuckTaskRuleCreation newCreation() {
        return StuckTaskRuleCreation.builder()
                .name("FT rule " + UUID.randomUUID())
                .procedureKeyword("labour")
                .thresholdDays(20)
                .penaltyAmount(new BigDecimal("75.50"))
                .userId(USER.getId())
                .build();
    }
}
