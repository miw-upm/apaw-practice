package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipmentEntityTest {

    @Test
    void testGetTotalPrice(){
        EquipmentEntity equipment = new EquipmentEntity("Bebida", 5, new BigDecimal("7.25"));
        assertEquals(new BigDecimal("36.25"), equipment.getTotalPrice());
    }
}
