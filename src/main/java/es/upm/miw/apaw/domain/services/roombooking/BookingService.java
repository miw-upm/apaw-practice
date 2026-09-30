package es.upm.miw.apaw.domain.services.roombooking;

import es.upm.miw.apaw.adapters.out.roombooking.postgres.UserBookingStat;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.roombooking.*;
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

    public List<Booking> find(BookingFindCriteria criteria) {
        List<Booking> bookings = this.bookingGateway.find(criteria);
        if (bookings.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = bookings.stream()
                .map(booking -> booking.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        return this.toSummaries(criteria, bookings, this.userFinder.findByIds(userIds));
    }

    private List<Booking> toSummaries(
            BookingFindCriteria criteria,
            List<Booking> bookings,
            List<UserSnapshot> users) {
        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return bookings.stream()
                .map(booking -> this.enrichUserSnapshot(booking, usersById))
                .filter(booking -> this.matchesUserEmail(criteria, booking))
                .map(Booking::ofSummary)
                .toList();
    }

    private Booking enrichUserSnapshot(Booking booking, Map<UUID, UserSnapshot> usersById) {
        UUID userId = booking.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        booking.setUserSnapshot(user);
        return booking;
    }

    private boolean matchesUserEmail(BookingFindCriteria criteria, Booking booking) {
        return !criteria.hasUserEmail()
                || criteria.getUserEmail().equalsIgnoreCase(booking.getUserSnapshot().getEmail());
    }
}