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
    void testFindAll() {
        assertEquals(4,playerSeriesRepository.findAll().size());
    }
}
