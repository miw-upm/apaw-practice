package es.upm.miw.apaw.domain.services.roombooking;

import es.upm.miw.apaw.adapters.out.roombooking.postgres.UserBookingStat;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.CreationBooking;
import es.upm.miw.apaw.domain.model.roombooking.Room;
import es.upm.miw.apaw.domain.model.roombooking.UserBookingReport;
import es.upm.miw.apaw.domain.ports.out.roombooking.BookingGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingGateway bookingGateway;
    private final RoomService roomService;
    private final UserFinder userFinder;

    @Transactional
    public Booking create(CreationBooking creationBooking) {
        UserSnapshot userSnapshot = this.userFinder.read(creationBooking.getUserId());
        Room room = this.roomService.read(creationBooking.getRoomId());

        Booking booking = Booking.builder()
                .name(creationBooking.getName())
                .estimatedAttendees(creationBooking.getEstimatedAttendees())
                .startDateTime(creationBooking.getStartDateTime())
                .endDateTime(creationBooking.getEndDateTime())
                .room(room)
                .userSnapshot(userSnapshot)
                .build();

        booking.doDefault();
        return this.bookingGateway.create(booking);
    }

    public List<UserBookingReport> findUserBookingReports() {
        List<UserBookingStat> stats = this.bookingGateway.findUserBookingStats();
        Set<UUID> userIds = stats.stream()
                .map(UserBookingStat::getUserId)
                .collect(Collectors.toSet());

        Map<UUID, UserSnapshot> userMap = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));

        return stats.stream()
                .map(stat -> UserBookingReport.builder()
                        .userSnapshot(userMap.get(stat.getUserId()))
                        .totalBookings(stat.getTotalBookings())
                        .totalAttendees(stat.getTotalAttendees())
                        .build())
                .toList();
    }
}