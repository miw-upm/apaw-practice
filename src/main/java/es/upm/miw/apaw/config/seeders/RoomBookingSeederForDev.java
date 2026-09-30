package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.roombooking.postgres.BookingEntity;
import es.upm.miw.apaw.adapters.out.roombooking.postgres.BookingRepository;
import es.upm.miw.apaw.adapters.out.roombooking.postgres.RoomEntity;
import es.upm.miw.apaw.adapters.out.roombooking.postgres.RoomRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class RoomBookingSeederForDev implements ApplicationRunner {

    public static final String PREFIX = "11111111-2222-3333-4444-55555555";

    public static final UUID USER_ID_0 = UUID.fromString(PREFIX + "9000");

    public static final UUID ROOM_ID_0 = UUID.fromString(PREFIX + "0000");
    public static final Room ROOM_0 = Room.builder()
            .id(ROOM_ID_0)
            .name("Auditorium A")
            .capacity(100)
            .floor(1)
            .videoconferenceEquipped(true)
            .createdAt(LocalDateTime.of(2025, 1, 10, 8, 0))
            .build();

    public static final UUID ROOM_ID_1 = UUID.fromString(PREFIX + "0001");
    public static final Room ROOM_1 = Room.builder()
            .id(ROOM_ID_1)
            .name("Boardroom B")
            .capacity(12)
            .floor(2)
            .videoconferenceEquipped(true)
            .createdAt(LocalDateTime.of(2025, 1, 11, 9, 30))
            .build();

    public static final UUID ROOM_ID_2 = UUID.fromString(PREFIX + "0002");
    public static final Room ROOM_2 = Room.builder()
            .id(ROOM_ID_2)
            .name("Classroom C")
            .capacity(30)
            .floor(3)
            .videoconferenceEquipped(false)
            .createdAt(LocalDateTime.of(2025, 1, 12, 10, 15))
            .build();

    public static final UUID ROOM_ID_3 = UUID.fromString(PREFIX + "0003");
    public static final Room ROOM_3 = Room.builder()
            .id(ROOM_ID_3)
            .name("Meeting Room D")
            .capacity(8)
            .floor(1)
            .videoconferenceEquipped(false)
            .createdAt(LocalDateTime.of(2025, 1, 13, 11, 0))
            .build();

    public static final UUID BOOKING_ID_0 = UUID.fromString(PREFIX + "1000");
    public static final Booking BOOKING_0 = Booking.builder()
            .id(BOOKING_ID_0)
            .name("Annual Tech Conference")
            .estimatedAttendees(50)
            .startDateTime(LocalDateTime.of(2026, 11, 1, 9, 0))
            .endDateTime(LocalDateTime.of(2026, 11, 1, 18, 0))
            .createdAt(LocalDateTime.of(2025, 1, 15, 9, 0))
            .room(ROOM_0)
            .userSnapshot(UserSnapshot.builder().id(USER_ID_0).build())
            .build();

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load RoomBooking from JAVA -----------");
        this.seedRooms();
        this.seedBookings();
    }

    private void seedRooms() {
        List<RoomEntity> rooms = List.of(ROOM_0, ROOM_1, ROOM_2, ROOM_3).stream()
                .filter(room -> !this.roomRepository.existsById(room.getId()))
                .map(RoomEntity::new)
                .toList();
        this.roomRepository.saveAll(rooms);
        log.warn("        ------- rooms: {} added", rooms.size());
    }

    private void seedBookings() {
        List<BookingEntity> bookings = List.of(BOOKING_0).stream()
                .filter(booking -> !this.bookingRepository.existsById(booking.getId()))
                .map(BookingEntity::new)
                .toList();
        this.bookingRepository.saveAll(bookings);
        log.warn("        ------- bookings: {} added", bookings.size());
    }
}