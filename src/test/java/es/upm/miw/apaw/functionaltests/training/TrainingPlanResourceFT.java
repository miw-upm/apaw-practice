package es.upm.miw.apaw.functionaltests.training;

import es.upm.miw.apaw.adapters.in.training.TrainingPlanResource;
import es.upm.miw.apaw.config.seeders.TrainingSeederForDev;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.training.TrainingPlan;
import es.upm.miw.apaw.domain.model.training.CreationTrainingPlan;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TrainingPlanResourceFT {

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
        UUID mockUserId = UUID.randomUUID();
        UserSnapshot mockUser = UserSnapshot.builder()
                .id(mockUserId)
                .mobile("666555444")
                .firstName("MockName")
                .build();
        
        given(userFinder.findByIds(any())).willReturn(List.of(mockUser));

        CreationTrainingPlan creation = CreationTrainingPlan.builder()
                .planCode("TP-" + UUID.randomUUID().toString())
                .courseIds(List.of(TrainingSeederForDev.COURSE_ID_1))
                .userIds(List.of(mockUserId))
                .build();

        this.restTestClient.post().uri(TrainingPlanResource.TRAINING_PLANS)
                .body(creation)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TrainingPlan.class)
                .value(body -> {
                    assertNotNull(body.getId());
                    assertNotNull(body.getApprovalDate());
                });
    }
    
    @Test
    void testCreateConflict() {
        UUID mockUserId = UUID.randomUUID();
        UserSnapshot mockUser = UserSnapshot.builder()
                .id(mockUserId)
                .build();
        
        given(userFinder.findByIds(any())).willReturn(List.of(mockUser));

        CreationTrainingPlan creation = CreationTrainingPlan.builder()
                .planCode("TP-CONFLICT")
                .courseIds(List.of(TrainingSeederForDev.COURSE_ID_1))
                .userIds(List.of(mockUserId))
                .build();

        this.restTestClient.post().uri(TrainingPlanResource.TRAINING_PLANS)
                .body(creation)
                .exchange()
                .expectStatus().isOk();

        this.restTestClient.post().uri(TrainingPlanResource.TRAINING_PLANS)
                .body(creation)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}

