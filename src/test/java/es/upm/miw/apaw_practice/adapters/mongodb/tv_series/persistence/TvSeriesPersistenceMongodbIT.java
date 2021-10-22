package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.TestConfig;

import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class TvSeriesPersistenceMongodbIT {

    @Autowired
    private TvSeriesPersistenceMongodb tvSeriesPersistence;

    @Test
    void testReadAll() {
        assertEquals(4,this.tvSeriesPersistence.findAll().count());
    }

    @Test
    void testDeleteByTitle() {
        this.tvSeriesPersistence.deleteByTitle("Kimetsu No Yaiba");
        assertEquals(3,this.tvSeriesPersistence.findAll().count());
    }

    @Test
    void testUpdateAndCreate() {
        TvSeries tvSeriesBD = this.tvSeriesPersistence.read("Kimetsu No Yaiba");
        assertFalse(tvSeriesBD.isFinished());
        tvSeriesBD.setFinished(true);
        this.tvSeriesPersistence.update(tvSeriesBD.getTitle(),tvSeriesBD);
        assertTrue(tvSeriesBD.isFinished());
        tvSeriesBD.setFinished(false);
        this.tvSeriesPersistence.update(tvSeriesBD.getTitle(),tvSeriesBD);
        assertFalse(tvSeriesBD.isFinished());
    }
}
