package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class TvSeriesPersistenceMongodbIT {

    @Autowired
    private TvSeriesPersistenceMongodb tvSeriesPersistence;

    @Test
    void testReadAll() {
        assertEquals(4,this.tvSeriesPersistence.readAll().count());
    }

    @Test
    void testDeleteByTitle() {
        this.tvSeriesPersistence.deleteByTitle("Kimetsu No Yaiba");
        assertEquals(3,this.tvSeriesPersistence.readAll().count());
    }
}
