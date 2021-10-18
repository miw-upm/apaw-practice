package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PlatformRepositoryIT {

    @Autowired
    private PlatformRepository platformRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.platformRepository.findAll().stream()
                .anyMatch(platform ->
                        "ps5".equals(platform.getModel()) &&
                                platform.getId() != null &&
                                platform.getConsoleName() != null &&
                                "playstation".equals(platform.getConsoleName()) &&
                                platform.getMemory() != null &&
                                "825gb".equals(platform.getMemory())
                ));
    }
}
