package es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;

public interface ReservationPersistence {

    Reservation read(String ownerName);
    void delete(String ownerName);
}
