package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.tv_series.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@TestConfig
class PlayerSeriesPersistenceMongodbIT {

    @Autowired
    private PlayerSeriesPersistenceMongodb playerSeriesPersistence;

    @Test
    void testReadAll() {
        List<Player> players = this.playerSeriesPersistence.findAll()
                .collect(Collectors.toList());
        assertEquals(4, players.size());
        assertEquals("Yuki Kaji",players.get(0).getName());
    }
}
