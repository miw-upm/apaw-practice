package es.upm.miw.apaw.functionaltests.roombooking;

import es.upm.miw.apaw.adapters.in.roombooking.BookingResource;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.CreationBooking;
import es.upm.miw.apaw.domain.model.roombooking.UserBookingReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.BOOKING_ID_0;
import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.ROOM_ID_0;
import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.USER_ID_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(userId)
                .firstName("John")
                .familyName("Doe")
                .email("john.doe@email.com")
                .mobile("600000100")
                .build();

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Retrospective " + UUID.randomUUID())
                .estimatedAttendees(15)
                .startDateTime(LocalDateTime.of(2026, 12, 10, 16, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 10, 17, 0))
                .roomId(ROOM_ID_0)
                .userId(userId)
                .build();

        when(this.userFinder.read(userId)).thenReturn(userSnapshot);

        this.restTestClient.post().uri(BookingResource.BOOKINGS)
                .body(creationBooking)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Booking.class)
                .value(booking -> {
                    assertThat(booking).isNotNull();
                    assertThat(booking.getId()).isNotNull();
                    assertThat(booking.getName()).isEqualTo(creationBooking.getName());
                    assertThat(booking.getEstimatedAttendees()).isEqualTo(15);
                    assertThat(booking.getRoom().getId()).isEqualTo(ROOM_ID_0);
                    assertThat(booking.getUserSnapshot().getId()).isEqualTo(userId);
                });
    }

    @Test
    void testCreateBadRequest() {
        CreationBooking creationBooking = CreationBooking.builder()
                .name(" ")
                .build();

        this.restTestClient.post().uri(BookingResource.BOOKINGS)
                .body(creationBooking)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateUserNotFound() {
        UUID userId = UUID.randomUUID();
        CreationBooking creationBooking = CreationBooking.builder()
                .name("Project Kickoff " + UUID.randomUUID())
                .estimatedAttendees(20)
                .startDateTime(LocalDateTime.of(2026, 12, 15, 9, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 15, 11, 0))
                .roomId(ROOM_ID_0)
                .userId(userId)
                .build();

        when(this.userFinder.read(userId))
                .thenThrow(new NotFoundException("User id not found: " + userId));

        this.restTestClient.post().uri(BookingResource.BOOKINGS)
                .body(creationBooking)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindUserBookingReports() {
        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(USER_ID_0)
                .firstName("John")
                .familyName("Doe")
                .email("john.doe@email.com")
                .mobile("600000100")
                .build();

        when(this.userFinder.findByIds(any())).thenReturn(List.of(userSnapshot));

        this.restTestClient.get().uri(BookingResource.BOOKINGS + BookingResource.REPORT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserBookingReport[].class)
                .value(reports -> {
                    assertThat(reports).extracting(UserBookingReport::getTotalBookings)
                            .isSortedAccordingTo(Comparator.reverseOrder());

                    assertThat(reports).filteredOn(report -> report.getUserSnapshot() != null
                                    && report.getUserSnapshot().getId().equals(USER_ID_0))
                            .singleElement()
                            .satisfies(report -> {
                                assertThat(report.getTotalBookings()).isGreaterThanOrEqualTo(1L);
                                assertThat(report.getTotalAttendees()).isGreaterThanOrEqualTo(50L);
                                assertThat(report.getUserSnapshot().getFirstName()).isEqualTo("John");
                            });
                });
    }

    @Test
    void testFind() {
        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(USER_ID_0)
                .firstName("John")
                .familyName("Doe")
                .email("john.doe@email.com")
                .mobile("600000100")
                .build();

        when(this.userFinder.findByIds(any())).thenReturn(List.of(userSnapshot));

        this.restTestClient.get().uri(uriBuilder -> uriBuilder
                        .path(BookingResource.BOOKINGS)
                        .queryParam("estimatedAttendees", 50)
                        .queryParam("ongoing", false)
                        .queryParam("videoconferenceEquipped", true)
                        .queryParam("userEmail", "john.doe@email.com")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Booking[].class)
                .value(bookings -> {
                    assertThat(bookings)
                            .extracting(Booking::getId)
                            .contains(BOOKING_ID_0);
                });
    }
}