package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreenPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfig
class ScreenServiceTest {

    @Autowired
    private ScreenService screenService;

    @Autowired
    private ScreenPersistence screenPersistence;

    @Test

    void testUpdateSpectators() {
        Integer number = 2;
        List<Spectator> spectatorList = List.of(new Spectator[]{
                new Spectator("544578J", "Maria", "Vernia", LocalDate.of(2021, 04, 10)),
                new Spectator("544588J", "Marta", "Vernia", LocalDate.of(2021, 04, 10)),
                new Spectator("994578J", "Javier", "Vernia", LocalDate.of(2021, 04, 10))
        });
        Screen screen = screenService.updateSpectators(number, spectatorList);
        assertEquals(spectatorList, screen.getSpectators());
        assertEquals(3, screen.getSpectators().size());
    }
}
