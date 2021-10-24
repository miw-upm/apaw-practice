package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.PlatformMemoryUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlatformPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class PlatformServiceIT {

    @Autowired
    private PlatformService platformService;

    @Autowired
    private PlatformPersistence platformPersistence;

    @Test
    void testUpdateMemory() {
        List<PlatformMemoryUpdating> platformMemoryUpdatingList = List.of(
                new PlatformMemoryUpdating("ps5", "1tb"),
                new PlatformMemoryUpdating("series x", "1.5tb")
        );
        this.platformService.updateMemory(platformMemoryUpdatingList.stream());
        assertEquals(0, "1tb".compareTo(this.platformPersistence.read("ps5").getMemory()));
        assertEquals(0, "1.5tb".compareTo(this.platformPersistence.read("series x").getMemory()));
        platformMemoryUpdatingList = List.of(
                new PlatformMemoryUpdating("ps5", "825gb"),
                new PlatformMemoryUpdating("series x", "1tb")
        );
        this.platformService.updateMemory(platformMemoryUpdatingList.stream());
    }
}
