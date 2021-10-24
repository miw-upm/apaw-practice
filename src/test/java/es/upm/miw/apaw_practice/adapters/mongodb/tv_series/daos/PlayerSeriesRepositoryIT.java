package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfig
class PlayerSeriesRepositoryIT {

    @Autowired
    private PlayerSeriesRepository playerSeriesRepository;

    @Test
    void testFindAll() {
        assertEquals(4,playerSeriesRepository.findAll().size());
    }
}
