package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {
    boolean existsByCarId(UUID carId);

    @Query("""
        SELECT new es.upm.miw.apaw.adapters.out.carreservation.postgres.UserReservationAggregation(
            c.registration,
            r.userId,
            COUNT(r),
            SUM(r.durationMinutes)
        )
        FROM ReservationEntity r
        JOIN r.car c
        GROUP BY c.registration, r.userId
        ORDER BY SUM(r.durationMinutes) DESC
    """)
    List<CarUsageRawReport> findRawCarUsageReport();
}