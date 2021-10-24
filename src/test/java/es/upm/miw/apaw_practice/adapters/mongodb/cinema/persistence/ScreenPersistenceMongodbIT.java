package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ScreenPersistenceMongodbIT {

    @Autowired
    private ScreenPersistenceMongodb screenPersistenceMongodb;

    @Test
    void testReadByNumber() {
        Screen screen = this.screenPersistenceMongodb.readByNumber(2);
        assertEquals(screen.getFlat(), 1);
        assertEquals(screen.getNumberOfSeats(), 70);
    }

    @Test
    void testUpdate() {
        List<Spectator> spectatorList = List.of(new Spectator[]{
                new Spectator("544578J", "Maria", "Vernia", LocalDate.of(2021, 04, 10)),
                new Spectator("544588J", "Marta", "Vernia", LocalDate.of(2021, 04, 10)),
                new Spectator("994578J", "Javier", "Vernia", LocalDate.of(2021, 04, 10))
        });
        Screen screen = this.screenPersistenceMongodb.readByNumber(1);
        Screen newScreen = this.screenPersistenceMongodb.update(screen, spectatorList);
        assertEquals(screen.getSpectators(), spectatorList);
    }

    @Test
    void testGetActorsNameByScreenNumber() {
        List<String> nameList = this.screenPersistenceMongodb.getActorsNameByScreenNumber(2);
        assertEquals(4, nameList.size());
    }
}
