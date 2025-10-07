package es.upm.miw.apaw.domain.services.winery;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
public class ReservationServiceIT {

    @Autowired
    private ReservationService reservationService;

    @MockitoBean
    private UserRestClient userRestClient;

    @Test
    void testCreate() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder()
                                .id(invocation.getArgument(0))
                                .mobile("123456789")
                                .firstName("mock")
                                .build());

        UUID tastingSessionId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");

        Reservation reservation = Reservation.builder()
                .user(UserDto.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .build())
                .tastingSession(TastingSession.builder()
                        .id(tastingSessionId)
                        .build())
                .build();

        Reservation reservationCreated = this.reservationService.create(reservation);

        assertThat(reservationCreated).isNotNull();
        assertThat(reservationCreated.getId()).isNotNull();
        assertThat(reservationCreated.getBookingDate()).isNotNull();
        assertThat(reservationCreated.getUser())
                .isNotNull()
                .extracting(UserDto::getId, UserDto::getMobile, UserDto::getFirstName)
                .containsExactly(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"), "123456789", "mock");
        assertThat(reservationCreated.getTastingSession()).isNotNull();
        assertThat(reservationCreated.getTastingSession().getId()).isEqualTo(tastingSessionId);
    }
}
