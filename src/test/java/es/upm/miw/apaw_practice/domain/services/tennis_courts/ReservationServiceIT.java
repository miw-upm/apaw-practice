package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ReservationServiceIT {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private Tennis_CourtsSeederService seeder;

    private String stringDate;
    private String stringTime;
    private LocalDateTime date;

    @BeforeEach
    void beforeEach(){
        stringDate = "19:10:21";
        stringTime = "11:00";
        date = LocalDateTime.of(2021, 10, 19, 11, 0);
    }

    @Test
    void testExtractValuesFromString(){
        int[] expectedValues = {19,10,21};
        assertEquals(expectedValues.length, ReservationService.extractValuesFromString(stringDate).length);
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], ReservationService.extractValuesFromString(stringDate)[i]);
        }
    }

    @Test
    void testExtractDateFromString(){
        assertEquals(this.date, ReservationService.extractDateFromString(stringDate, stringTime));
    }

    @Test
    void testUpdatePlayerList(){
        Reservation reservation = new Reservation();
        reservation.setPlayers(List.of(new Player("00000001R"), new Player("00000002R")));
        List<Player> updatedPlayerList = this.reservationService.updatePlayerList("Nacho", "30:9:21", "11:00", reservation)
                .collect(Collectors.toList());
        assertEquals(4, updatedPlayerList.size());
        seeder.deleteAll();
        seeder.seedDatabase();
    }
}
