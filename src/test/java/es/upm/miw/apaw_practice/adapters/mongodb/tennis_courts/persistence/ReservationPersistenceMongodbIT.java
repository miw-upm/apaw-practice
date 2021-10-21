package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;
import es.upm.miw.apaw_practice.domain.services.tennis_courts.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ReservationPersistenceMongodbIT {

    @Autowired
    private ReservationPersistenceMongoDB reservationPersistenceMongoDB;
    @Autowired
    private Tennis_CourtsSeederService seeder;

    @Test
    void testDeleteAndRead(){
        LocalDate localDate = LocalDate.of(2021, 9, 30);
        this.reservationPersistenceMongoDB.delete("Paco", localDate.atTime( 11,0));
        assertThrows(NotFoundException.class, () -> this.reservationPersistenceMongoDB.read("Paco", localDate.atTime(11, 0)));
    }

    @Test
    void testUpdatePlayerList(){
        LocalDateTime requestDate = LocalDateTime.of(2021, 9, 30, 11, 0);
        Reservation reservation = new Reservation();
        reservation.setPlayers(List.of(new Player("00000001R"), new Player("00000002R")));
        List<Player> updatedPlayerList = this.reservationPersistenceMongoDB.updatePlayerList("Nacho", requestDate, reservation)
                .collect(Collectors.toList());
        assertEquals(4, updatedPlayerList.size());
        reservation.setPlayers(List.of(new Player("otro")));
        assertThrows(BadRequestException.class, () -> this.reservationPersistenceMongoDB.updatePlayerList("Nacho", requestDate, reservation));
        reservation.setPlayers(List.of(new Player("00000001R")));
        assertThrows(NotFoundException.class, () -> this.reservationPersistenceMongoDB.updatePlayerList("Sergio", requestDate, reservation));
        assertThrows(NotFoundException.class, () -> this.reservationPersistenceMongoDB.updatePlayerList("Nacho", requestDate.withHour(18), reservation));
        seeder.deleteAll();
        seeder.seedDatabase();
    }

}
