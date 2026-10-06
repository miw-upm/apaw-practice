package es.upm.miw.apaw.domain.services.carreservation;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.*;
import es.upm.miw.apaw.domain.ports.out.carreservation.ReservationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationGateway reservationGateway;
    private final CarService carService;
    private final UserFinder userFinder;

    public Reservation create(CreationReservation creationReservation) {
        Car car = this.carService.read(creationReservation.getCarId());

        Reservation reservation = new Reservation();

        BeanUtils.copyProperties(creationReservation, reservation);

        reservation.setCar(car);
        reservation.setUserSnapshot(this.userFinder.read(creationReservation.getUserId()));

        reservation.doDefault();

        return this.reservationGateway.create(reservation);
    }

    public List<CarUsageReport> findCarUsageReport() {
        return this.reservationGateway.findCarUsageReport();
    }

    public List<Reservation> find(ReservationFindCriteria criteria) {
        List<Reservation> reservations = this.reservationGateway.find(criteria);
        if (reservations.isEmpty()) {
            return List.of();
        }

        Set<UUID> userIds = reservations.stream()
                .map(reservation -> reservation.getUserSnapshot().getId())
                .collect(Collectors.toSet());

        return this.toSummaries(criteria, reservations, this.userFinder.findByIds(userIds));
    }

    private List<Reservation> toSummaries(
            ReservationFindCriteria criteria,
            List<Reservation> reservations,
            List<UserSnapshot> users) {

        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));

        return reservations.stream()
                .map(reservation -> this.enrichUserSnapshot(reservation, usersById))
                .filter(reservation -> this.matchesUserCity(criteria, reservation))
                .toList();
    }

    private Reservation enrichUserSnapshot(Reservation reservation, Map<UUID, UserSnapshot> usersById) {
        UUID userId = reservation.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        reservation.setUserSnapshot(user);
        return reservation;
    }

    private boolean matchesUserCity(ReservationFindCriteria criteria, Reservation reservation) {
        return !criteria.hasUserCity()
                || criteria.getUserCity().equalsIgnoreCase(reservation.getUserSnapshot().getCity());
    }
}