package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.TvSeriesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class TvSeriesRepositoryIT {
    @Autowired
    private TvSeriesRepository tvSeriesRepository;

    @Test
    void testFindByTitle() {
        assertTrue(this.tvSeriesRepository.findByTitle("Fairy Tail").isPresent());
        TvSeriesEntity tvSeriesEntity = this.tvSeriesRepository.findByTitle("Fairy Tail").get();
        assertEquals(2009,tvSeriesEntity.getYear());
        assertTrue(tvSeriesEntity.isFinished());
        assertEquals("A-1 Pictures Inc.",tvSeriesEntity.getProducerEntity().getBusinessName());
        assertEquals(37,tvSeriesEntity.getEpisodeEntities().size());
    }
}
