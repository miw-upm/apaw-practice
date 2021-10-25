package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistance.PlatformPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class PlatformPersistenceMongodbIT {

    @Autowired
    public PlatformPersistenceMongodb platformPersistenceMongodb;

    @Test
    void testReadAll() {
        Stream<Platform> platformStream = this.platformPersistenceMongodb.readAll();
        assertNotNull(platformStream);
    }

    @Test
    void testCreateAndRead() {
        Platform platform =
                new Platform("intellivision", "2", "12kb");
        this.platformPersistenceMongodb.create(platform);
        Platform platformBD = this.platformPersistenceMongodb.read("intellivision");
        assertEquals("2", platformBD.getModel());
        assertEquals("12kb", platformBD.getMemory());
    }

    @Test
    void testCreateAndUpdate() {
        Platform platformCreation =
                new Platform("mega drive", "genesis", "256mb");
        Platform platformBD = this.platformPersistenceMongodb.create(platformCreation);
        platformBD.setMemory("32mb");
        this.platformPersistenceMongodb.update("mega drive", platformBD);
        platformBD = this.platformPersistenceMongodb.read("mega drive");
        assertEquals("32mb", platformBD.getMemory());
    }

}
