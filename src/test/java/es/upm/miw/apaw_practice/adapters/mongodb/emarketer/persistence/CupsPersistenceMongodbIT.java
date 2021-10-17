package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.EmarketerSeederService;
import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class CupsPersistenceMongodbIT {

    @Autowired
    private CupsPersistenceMongodb cupsPersistenceMongodb;

    @Autowired
    private EmarketerSeederService emarketerSeederService;

    @Test
    void testUpdate() {
        Cups cups = this.cupsPersistenceMongodb.readByCup("AAPPZZZ6KZ1R149946");
        assertEquals(new BigDecimal("912.31"), cups.getEnergy());
        cups.setEnergy(new BigDecimal("942.31"));
        this.cupsPersistenceMongodb.update(cups);
        Cups cupsUpdated = this.cupsPersistenceMongodb.readByCup("AAPPZZZ6KZ1R149946");
        assertEquals(new BigDecimal("942.31"), cupsUpdated.getEnergy());
        emarketerSeederService.deleteAll();
        emarketerSeederService.seedDatabase();
    }


}
