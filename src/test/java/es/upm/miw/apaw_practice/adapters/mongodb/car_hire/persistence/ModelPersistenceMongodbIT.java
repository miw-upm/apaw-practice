package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ModelPersistenceMongodbIT {

    @Autowired
    ModelPersistenceMongodb modelPersistenceMongodb;

    @Test
    void testReadByVinNumberNotFound() {
        assertThrows(NotFoundException.class, () -> this.modelPersistenceMongodb.readByVinNumber("invented"));
    }

    @Test
    void testReadByVinNumber() {
        Model model = this.modelPersistenceMongodb.readByVinNumber("VSSZZZ6KZ1R149943");
        assertEquals("Opel Insignia", model.getType());
        assertEquals("Tipo Berlina, manual", model.getDescription());
        assertEquals(140, model.getEnginePower());
        assertEquals("VSSZZZ6KZ1R149943", model.getVehicleList().get(0).getVinNumber());
        assertEquals(0, model.getVehicleList().get(0).getDailyCost().compareTo(new BigDecimal("50")));
        assertEquals(25400, model.getVehicleList().get(0).getKilometersAmount());
        assertEquals(Boolean.TRUE, model.getVehicleList().get(0).getGoodCondition());
    }

    @Test
    void testAssertExistVinNumber() {
        assertFalse(this.modelPersistenceMongodb.assertExistVinNumber("Invented"));
        assertTrue(this.modelPersistenceMongodb.assertExistVinNumber("VSSZZZ6KZ1R149943"));
    }
}


