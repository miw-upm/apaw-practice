package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.TestConfig;
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
    void testFindScreenNumberOfSeats() {
        List<ScreenEntity> screens = this.screenRepository.findAll();

        assertEquals(screens.get(1).getNumberOfSeats(), 70);
    }
}
