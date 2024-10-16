package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class PlayerPersistenceMongodbIT {

    @Autowired
    private PlayerPersistenceMongodb playerPersistence;

    @Test
    void testReadByPlayerName() {
        Player player = this.playerPersistence.readyByPlayerName("Luis");
        assertEquals(29, player.getAge());
        assertEquals(LocalDate.of(1995,9,16), player.getBirthday());
        assertEquals("Xbox", player.getConsole().getConsoleReference());
    }

    @Test
    void testFindVideoGameAliasByPlayerName(){
        Stream<String> playerNames = this.playerPersistence.findVideoGameAliasByPlayerName("Julia");
        assertEquals(List.of("Call of Duty"), playerNames.toList());
    }
}
