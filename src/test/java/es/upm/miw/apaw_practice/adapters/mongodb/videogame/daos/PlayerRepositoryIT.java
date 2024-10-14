package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlayerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestConfig
public class PlayerRepositoryIT {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void testFindByPlayerName(){
        Optional<PlayerEntity> playerEntity = playerRepository.findByPlayerName("Luis");
        assertFalse(playerEntity.isEmpty());
        assertNotNull(playerEntity.get());
        PlayerEntity player = playerEntity.get();
        assertEquals(29, player.getAge());
        assertEquals(LocalDate.of(1995,9,16), player.getBirthday());
        ConsoleEntity console = player.getConsoleEntity();
        assertEquals("Xbox",console.getConsoleReference());
        assertEquals(9875456464646L, console.getSerialNumber());
        assertTrue(console.getVideoGameEntities().stream()
                .map(VideoGamerEntity::getVideoGameAlias)
                .toList()
                .contains("Halo"));
        assertTrue(console.getPortable());
    }
}
