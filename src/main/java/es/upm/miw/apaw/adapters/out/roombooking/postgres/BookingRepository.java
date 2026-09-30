package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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

    @Query("SELECT b FROM BookingEntity b JOIN b.room r WHERE " +
            "(:estimatedAttendees IS NULL OR b.estimatedAttendees = :estimatedAttendees) AND " +
            "(:ongoing IS NULL OR " +
            " (:ongoing = true AND b.startDateTime <= :now AND b.endDateTime >= :now) OR " +
            " (:ongoing = false AND (b.startDateTime > :now OR b.endDateTime < :now))) AND " +
            "(:videoconferenceEquipped IS NULL OR r.videoconferenceEquipped = :videoconferenceEquipped)")
    List<BookingEntity> findByCriteria(
            @Param("estimatedAttendees") Integer estimatedAttendees,
            @Param("ongoing") Boolean ongoing,
            @Param("now") LocalDateTime now,
            @Param("videoconferenceEquipped") Boolean videoconferenceEquipped);
}