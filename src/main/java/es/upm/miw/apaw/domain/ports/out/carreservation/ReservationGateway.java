package es.upm.miw.apaw.domain.ports.out.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.Reservation;

public interface ReservationGateway {
    Reservation create(Reservation reservation);
}
