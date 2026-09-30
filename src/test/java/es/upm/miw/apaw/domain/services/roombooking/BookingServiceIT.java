package es.upm.miw.apaw.domain.services.roombooking;

import es.upm.miw.apaw.adapters.out.roombooking.postgres.BookingEntity;
import es.upm.miw.apaw.adapters.out.roombooking.postgres.BookingRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.RoomBookingSeederForDev.ROOM_ID_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class BookingServiceIT {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .firstName("John")
                .familyName("Doe")
                .email("john.doe@email.com")
                .mobile("600000100")
                .build();

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Booking " + UUID.randomUUID())
                .estimatedAttendees(10)
                .startDateTime(LocalDateTime.of(2026, 12, 1, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 1, 11, 0))
                .roomId(ROOM_ID_0)
                .userId(userSnapshot.getId())
                .build();

        when(this.userFinder.read(userSnapshot.getId())).thenReturn(userSnapshot);

        Booking booking = this.bookingService.create(creationBooking);

        assertThat(booking.getId()).isNotNull();
        assertThat(booking.getName()).isEqualTo(creationBooking.getName());
        assertThat(booking.getEstimatedAttendees()).isEqualTo(10);
        assertThat(booking.getStartDateTime()).isEqualTo(creationBooking.getStartDateTime());
        assertThat(booking.getEndDateTime()).isEqualTo(creationBooking.getEndDateTime());
        assertThat(booking.getRoom().getId()).isEqualTo(ROOM_ID_0);
        assertThat(booking.getUserSnapshot().getId()).isEqualTo(userSnapshot.getId());
        assertThat(booking.getCreatedAt()).isNotNull();

        BookingEntity entity = this.bookingRepository.findById(booking.getId()).orElseThrow();
        assertThat(entity.getName()).isEqualTo(creationBooking.getName());
        assertThat(entity.getEstimatedAttendees()).isEqualTo(10);
        assertThat(entity.getStartDateTime()).isEqualTo(creationBooking.getStartDateTime());
        assertThat(entity.getEndDateTime()).isEqualTo(creationBooking.getEndDateTime());
        assertThat(entity.getRoom().getId()).isEqualTo(ROOM_ID_0);
        assertThat(entity.getUserId()).isEqualTo(userSnapshot.getId());
    }

    @Test
    @Transactional
    void testCreateUserNotFound() {
        UUID userId = UUID.randomUUID();
        CreationBooking creationBooking = CreationBooking.builder()
                .name("Booking " + UUID.randomUUID())
                .estimatedAttendees(5)
                .startDateTime(LocalDateTime.of(2026, 12, 1, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 1, 11, 0))
                .roomId(ROOM_ID_0)
                .userId(userId)
                .build();

        when(this.userFinder.read(userId)).thenThrow(new NotFoundException("User id not found: " + userId));

        assertThatThrownBy(() -> this.bookingService.create(creationBooking))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(userId.toString());
    }

    @Test
    @Transactional
    void testCreateRoomNotFound() {
        UserSnapshot userSnapshot = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .firstName("John")
                .familyName("Doe")
                .email("john.doe@email.com")
                .mobile("600000100")
                .build();
        UUID missingRoomId = UUID.randomUUID();

        CreationBooking creationBooking = CreationBooking.builder()
                .name("Booking " + UUID.randomUUID())
                .estimatedAttendees(5)
                .startDateTime(LocalDateTime.of(2026, 12, 1, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 12, 1, 11, 0))
                .roomId(missingRoomId)
                .userId(userSnapshot.getId())
                .build();

        when(this.userFinder.read(userSnapshot.getId())).thenReturn(userSnapshot);

        assertThatThrownBy(() -> this.bookingService.create(creationBooking))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(missingRoomId.toString());
    }
}