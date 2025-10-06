package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.airport.Plane;
import es.upm.miw.apaw.domain.persistenceports.airport.PlanePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class PlaneServiceTest {

    @Autowired
    private PlaneService planeService;

    @MockitoBean
    private PlanePersistence planePersistence;

    @Test
    void testCreate() {
        BDDMockito.given(this.planePersistence.create(Mockito.any()))
                .willAnswer(invocation -> invocation.getArgument(0));

        Plane plane = Plane.builder()
                .registrationNumber("Test0")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.now().minusMonths(3))
                .manufacturer("Airbus")
                .build();

        assertThat(this.planeService.create(plane).getRegistrationNumber()).isEqualTo("Test0");
    }

    @Test
    void testCreateConflictException() {
        BDDMockito.given(this.planePersistence.existRegistrationNumber(Mockito.anyString()))
                .willReturn(true);

        Plane plane = Plane.builder()
                .registrationNumber("Test1")
                .model("A320neo")
                .seatCount(186)
                .createdAt(LocalDateTime.now().minusMonths(3))
                .manufacturer("Airbus")
                .build();

        assertThatThrownBy(() -> this.planeService.create(plane))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Test1");
    }
}
