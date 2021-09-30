package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.SpectatorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class SpectatorRepositoryIT {

    @Autowired
    private  SpectatorRepository spectatorRepository;

    @Test
    void testFindAllScreens() {
        List<SpectatorEntity> spectators = this.spectatorRepository.findAll();

        assertEquals(spectators.size(), 4);
        assertEquals(spectators.get(1).getName(), "John");
    }

}
