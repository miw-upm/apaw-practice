package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.PlayerSeriesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PlayerSeriesRepositoryIT {

    @Autowired
    private PlayerSeriesRepository playerSeriesRepository;

    @Test
    void testFindByName() {
        assertTrue(this.playerSeriesRepository.findByName("Brittney Karbowski").findFirst().isPresent());
        List<PlayerSeriesEntity> playerSeriesEntities = this.playerSeriesRepository
                .findByName("Brittney Karbowski")
                .collect(Collectors.toList());
        assertEquals(1, playerSeriesEntities.size());
        assertEquals(LocalDate.of(1986, 6, 26), playerSeriesEntities.get(0).getBirth());
        assertEquals("United States", playerSeriesEntities.get(0).getNationality());
        assertEquals("Fairy Tail", playerSeriesEntities.get(0).getTvSeriesEntities().get(0).getTitle());
    }
}
