package es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Court;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface ReservationPersistence {

    Reservation read(String ownerName, LocalDateTime date);
    void delete(String ownerName, LocalDateTime date);
    Stream<Player> updatePlayerList(String ownerName, LocalDateTime date, Reservation reservation);
    Court get(String ownerName, LocalDateTime date);
}
