package es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;

import java.time.LocalDateTime;

public interface ReservationPersistence {

    Reservation read(String ownerName, LocalDateTime date);
    void delete(String ownerName, LocalDateTime date);
}
