package es.upm.miw.apaw.functionaltests.expertdirectoryservices;

import es.upm.miw.apaw.adapters.in.expertdirectoryservices.ExpertServiceScheduleResource;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.expertdirectoryservices.ExpertServiceSchedule;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ExpertDirectoryServicesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ExpertServiceScheduleResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(id -> UserSnapshot.builder().id(id).firstName("Hydrated")
                    .email(id + "@test.com").build()).toList();
        });
    }

    @Test
    void testFindWithoutCriteria() {
        assertThat(this.find("")).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_0.getTariffCode(), SCHEDULE_1.getTariffCode());
    }

    @Test
    void testFindByMinRateAmount() {
        assertThat(this.find("?minRateAmount=150")).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_1.getTariffCode()).doesNotContain(SCHEDULE_0.getTariffCode());
    }

    @Test
    void testFindByWithSpecialCondition() {
        assertThat(this.find("?withSpecialCondition=true")).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_1.getTariffCode()).doesNotContain(SCHEDULE_0.getTariffCode());
        assertThat(this.find("?withSpecialCondition=false")).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_0.getTariffCode()).doesNotContain(SCHEDULE_1.getTariffCode());
    }

    @Test
    void testFindBySpecialtyArea() {
        assertThat(this.find("?specialtyArea={specialtyArea}", PROFILE_1.getSpecialtyArea()))
                .extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_0.getTariffCode()).doesNotContain(SCHEDULE_1.getTariffCode());
    }

    @Test
    void testFindByUserEmail() {
        String email = PROFILE_3.getUserSnapshot().getId() + "@test.com";

        List<ExpertServiceSchedule> schedules = this.find("?userEmail=" + email);

        assertThat(schedules).extracting(ExpertServiceSchedule::getTariffCode)
                .contains(SCHEDULE_1.getTariffCode()).doesNotContain(SCHEDULE_0.getTariffCode());
        assertThat(schedules.getFirst().getLegalExpertProfiles().getFirst().getUserSnapshot().getEmail())
                .isEqualTo(email);
    }

    @Test
    void testFindUserNotFound() {
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of());

        this.restTestClient.get().uri(ExpertServiceScheduleResource.EXPERT_SERVICE_SCHEDULES)
                .exchange()
                .expectStatus().isNotFound();
    }

    private List<ExpertServiceSchedule> find(String query, Object... uriVariables) {
        return List.of(this.restTestClient.get()
                .uri(ExpertServiceScheduleResource.EXPERT_SERVICE_SCHEDULES + query, uriVariables)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ExpertServiceSchedule[].class)
                .returnResult().getResponseBody());
    }
}
