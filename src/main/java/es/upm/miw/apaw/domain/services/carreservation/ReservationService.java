package es.upm.miw.apaw.domain.services.carreservation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.model.carreservation.CreationReservation;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import es.upm.miw.apaw.domain.ports.out.carreservation.ReservationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}