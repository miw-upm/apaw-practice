package es.upm.miw.apaw.domain.services.roombooking;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.CreationBooking;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class BookingServiceIT {

    @Autowired
    private BookingService bookingService;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testCreate() {
        UUID userId = UUID.randomUUID();
        when(this.userFinder.read(userId))
                .thenReturn(UserSnapshot.builder().id(userId).mobile("600111222").firstName("Alex").build());

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Sprint Planning")
                .estimatedAttendees(10)
                .startDateTime(LocalDateTime.of(2026, 12, 1, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 1, 11, 30))
                .roomId(ROOM_ID_0)
                .userId(userId)
                .build();

        Booking created = this.bookingService.create(creationBooking);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Sprint Planning");
        assertThat(created.getEstimatedAttendees()).isEqualTo(10);
        assertThat(created.getRoom().getId()).isEqualTo(ROOM_ID_0);
        assertThat(created.getUserSnapshot().getId()).isEqualTo(userId);
        assertThat(created.getCreatedAt()).isNotNull();
    }

    @Test
    void testCreateUserNotFound() {
        UUID userId = UUID.randomUUID();
        when(this.userFinder.read(userId))
                .thenThrow(new NotFoundException("User id not found: " + userId));

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Sprint Planning")
                .estimatedAttendees(10)
                .startDateTime(LocalDateTime.of(2026, 12, 1, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 1, 11, 30))
                .roomId(ROOM_ID_0)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> this.bookingService.create(creationBooking))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(userId.toString());
    }

    @Test
    void testCreateRoomNotFound() {
        UUID userId = UUID.randomUUID();
        UUID missingRoomId = UUID.randomUUID();

        when(this.userFinder.read(userId))
                .thenReturn(UserSnapshot.builder().id(userId).mobile("600111222").firstName("Alex").build());

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Sprint Planning")
                .estimatedAttendees(10)
                .startDateTime(LocalDateTime.of(2026, 12, 1, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 1, 11, 30))
                .roomId(missingRoomId)
                .userId(userId)
                .build();

        assertThatThrownBy(() -> this.bookingService.create(creationBooking))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingRoomId.toString());
    }
}