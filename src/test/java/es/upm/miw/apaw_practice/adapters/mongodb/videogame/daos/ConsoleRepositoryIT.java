package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ConsoleRepositoryIT {

    @Autowired
    private ConsoleRepository consoleRepository;

    @Test
    void testCreateAndRead(){
        assertTrue(this.consoleRepository.findByConsoleReference("Xbox").isPresent());
        ConsoleEntity console = this.consoleRepository.findByConsoleReference("Xbox").get();
        assertEquals("Xbox", console.getConsoleReference());
        assertEquals(9875456464646L,console.getSerialNumber());
        assertFalse(console.getVideoGameEntities().stream()
                .map(VideoGamerEntity::getVideoGameAlias)
                .toList()
                .contains(Arrays.asList("Halo","Call of Duty")));
        assertTrue(console.getPortable());
    }
}