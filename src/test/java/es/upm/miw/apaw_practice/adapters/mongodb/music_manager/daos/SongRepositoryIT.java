package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class SongRepositoryIT {

    @Autowired
    SongRepository songRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.songRepository.findAll().stream()
                .anyMatch(song ->
                        "Blackbird".equals(song.getSongTitle()) &&
                                song.getId() != null &&
                                song.getGenre().equals("Folk") &&
                                song.getLength().equals(139)));
    }
}