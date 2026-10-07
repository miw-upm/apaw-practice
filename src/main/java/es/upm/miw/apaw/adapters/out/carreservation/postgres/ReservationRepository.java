package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID>, JpaSpecificationExecutor<ReservationEntity> {
    boolean existsByCarId(UUID carId);

    @Query("""
        SELECT new es.upm.miw.apaw.adapters.out.carreservation.postgres.CarUsageRawReport(
            car.licensePlate,
            r.userId,
            COUNT(r),
            COALESCE(SUM(r.durationMinutes), 0L)
        )
        FROM ReservationEntity r
        JOIN r.car car
        GROUP BY car.licensePlate, r.userId
        ORDER BY COALESCE(SUM(r.durationMinutes), 0L) DESC
    """)
    List<CarUsageRawReport> findRawCarUsageReport();
}