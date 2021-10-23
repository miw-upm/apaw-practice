package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CourtPersistenceMongodbIT {

    @Autowired
    private CourtPersistenceMongoDB courtPersistence;

    @Test
    void testGetEquipmentValues(){
        BigDecimal[] expectedValues = {
                new BigDecimal("10.0"),
                new BigDecimal("7.5")
        };
        List<BigDecimal> valuesStream = this.courtPersistence.getEquipmentValues(2).collect(Collectors.toList());
        assertEquals(2, valuesStream.size());
        for(int i = 0; i < expectedValues.length; i++){
            assertEquals(expectedValues[i], valuesStream.get(i));
        }

        assertTrue(this.courtPersistence.getEquipmentValues(4).findFirst().isEmpty());
        assertThrows(NotFoundException.class, () -> this.courtPersistence.getEquipmentValues(5));
    }
}
