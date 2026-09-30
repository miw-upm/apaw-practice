package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    boolean existsByRoomId(UUID roomId);

    @Query("SELECT new es.upm.miw.apaw.adapters.out.roombooking.postgres.UserBookingStat(" +
            "b.userId, COUNT(b.id), SUM(b.estimatedAttendees)) " +
            "FROM BookingEntity b JOIN b.room r " +
            "GROUP BY b.userId " +
            "ORDER BY COUNT(b.id) DESC")
    List<UserBookingStat> findUserBookingStats();
}