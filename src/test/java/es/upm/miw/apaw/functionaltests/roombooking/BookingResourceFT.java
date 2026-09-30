package es.upm.miw.apaw.functionaltests.roombooking;

import es.upm.miw.apaw.adapters.in.roombooking.BookingResource;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.CreationBooking;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDateTime;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.ROOM_ID_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookingResourceFT {

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
    }

    @Test
    void testCreate() {
        UUID userId = UUID.randomUUID();
        when(this.userFinder.read(userId))
                .thenReturn(UserSnapshot.builder().id(userId).mobile("611222333").firstName("Maria").build());

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Retrospective")
                .estimatedAttendees(15)
                .startDateTime(LocalDateTime.of(2026, 12, 10, 16, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 10, 17, 0))
                .roomId(ROOM_ID_0)
                .userId(userId)
                .build();

        this.restTestClient.post().uri(BookingResource.BOOKINGS)
                .body(creationBooking)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Booking.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getName()).isEqualTo("Retrospective");
                    assertThat(body.getEstimatedAttendees()).isEqualTo(15);
                    assertThat(body.getRoom().getId()).isEqualTo(ROOM_ID_0);
                    assertThat(body.getUserSnapshot().getId()).isEqualTo(userId);
                });
    }

    @Test
    void testCreateNullFields() {
        CreationBooking creationBooking = CreationBooking.builder()
                .name("   ")
                .build();

        this.restTestClient.post().uri(BookingResource.BOOKINGS)
                .body(creationBooking)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(this.userFinder.read(userId))
                .thenThrow(new NotFoundException("User id not found: " + userId));

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Project Kickoff")
                .estimatedAttendees(20)
                .startDateTime(LocalDateTime.of(2026, 12, 15, 9, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 15, 11, 0))
                .roomId(ROOM_ID_0)
                .userId(userId)
                .build();

        this.restTestClient.post().uri(BookingResource.BOOKINGS)
                .body(creationBooking)
                .exchange()
                .expectStatus().isNotFound();
    }
}