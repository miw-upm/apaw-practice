package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.CarUsageReport;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.ports.out.carreservation.ReservationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReservationAdapter implements ReservationGateway {
    private final ReservationRepository reservationRepository;
    private final UserFinder userFinder;

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity(reservation);

        ReservationEntity savedEntity = this.reservationRepository.save(reservationEntity);

        return savedEntity.toDomain();
    }

    @Override
    public List<CarUsageReport> findCarUsageReport() {
        List<CarUsageRawReport> rawResults = this.reservationRepository.findRawCarUsageReport();

        if (rawResults.isEmpty()) {
            return List.of();
        }

        Set<UUID> userIds = rawResults.stream()
                .map(CarUsageRawReport::getUserId)
                .collect(Collectors.toSet());

        Map<UUID, UserSnapshot> userMap = this.userFinder.findByIds(userIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));

        return rawResults.stream()
                .map(raw -> CarUsageReport.builder()
                        .carRegistration(raw.getCarRegistration())
                        .userSnapshot(userMap.get(raw.getUserId()))
                        .totalReservations(raw.getTotalReservations())
                        .totalDurationMinutes(raw.getTotalDurationMinutes())
                        .build())
                .toList();
    }
}