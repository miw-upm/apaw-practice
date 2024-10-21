package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ConsoleServiceIT {
    @Autowired
    private ConsoleService consoleService;

    @Test
    void testFindByConsoleReference(){
        assertTrue(this.consoleService.findByConsoleReference("Bungie").toList().isEmpty());
        assertTrue(this.consoleService.findByConsoleReference("Amazones").toList().isEmpty());
        assertEquals(0, this.consoleService.findByConsoleReference("Microsoft").toList().size());
    }
}