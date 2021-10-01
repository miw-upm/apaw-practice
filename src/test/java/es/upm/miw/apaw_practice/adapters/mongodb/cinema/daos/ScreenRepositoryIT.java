package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class ScreenRepositoryIT {
    @Autowired
    private ScreenRepository screenRepository;

    @Test
    void testFindAllScreens() {
        List<ScreenEntity> screens = this.screenRepository.findAll();

        assertEquals(screens.size(), 2);
        assertEquals(screens.get(1).getNumberOfSeats(), 70);
    }
}
