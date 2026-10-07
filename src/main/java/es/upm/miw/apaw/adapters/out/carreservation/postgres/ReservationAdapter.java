package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.CarUsageReport;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.model.carreservation.ReservationFindCriteria;
import es.upm.miw.apaw.domain.ports.out.carreservation.ReservationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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
                        .userSnapshot(userMap.getOrDefault(
                                raw.getUserId(),
                                UserSnapshot.builder().id(raw.getUserId()).build()
                        ))
                        .totalReservations(raw.getTotalReservations())
                        .totalDurationMinutes(raw.getTotalDurationMinutes())
                        .build())
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> find(ReservationFindCriteria criteria) {
        return this.reservationRepository.findAll(this.buildSpecification(criteria)).stream()
                .map(ReservationEntity::toDomain)
                .toList();
    }

    private Specification<ReservationEntity> buildSpecification(ReservationFindCriteria criteria) {
        Specification<ReservationEntity> specification = (root, query, builder) -> {
            if (Long.class != query.getResultType()) {
                root.fetch("car", JoinType.LEFT);
            }
            return builder.conjunction();
        };

        if (criteria.hasDurationMinutes()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("durationMinutes"), criteria.getDurationMinutes()));
        }

        if (criteria.hasActive()) {
            specification = specification.and(this.active(criteria.getActive()));
        }

        if (criteria.hasCarLicensePlate()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("car").get("licensePlate"), criteria.getCarLicensePlate()));
        }

        return specification;
    }

    private Specification<ReservationEntity> active(boolean active) {
        return (root, query, builder) -> {
            LocalDate today = LocalDate.now();
            LocalTime nowTime = LocalTime.now();

            var isFutureDate = builder.greaterThan(root.get("date"), today);
            var isTodayAndFutureTime = builder.and(
                    builder.equal(root.get("date"), today),
                    builder.greaterThan(root.get("endTime"), nowTime)
            );
            var isFutureOrPresent = builder.or(isFutureDate, isTodayAndFutureTime);

            return active ? isFutureOrPresent : builder.not(isFutureOrPresent);
        };
    }
}