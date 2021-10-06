package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerEntityTest {

    private PlayerEntity playerEntity;
    private List<Equipment> equipments;

    @BeforeEach
    void beforeEach(){
        this.equipments = new ArrayList<>();
        this.equipments.add(new Equipment("Ball", 3, new BigDecimal("2.5")));
        this.equipments.add(new Equipment("Racquet", 2, new BigDecimal("5")));
        this.equipments.add(new Equipment("Shoes", 2, new BigDecimal("4")));
        this.playerEntity = new PlayerEntity();
    }

    @Test
    void testConvertToEntityList(){
        EquipmentEntity[] expectedValues = {
                new EquipmentEntity("Ball",3, new BigDecimal("2.5")),
                new EquipmentEntity("Racquet",2, new BigDecimal("5")),
                new EquipmentEntity("Shoes",2, new BigDecimal("4"))
        };
        assertEquals(expectedValues[0], this.playerEntity.convertToEntityList(equipments).get(0));
        assertEquals(expectedValues[1], this.playerEntity.convertToEntityList(equipments).get(1));
        assertEquals(expectedValues[2], this.playerEntity.convertToEntityList(equipments).get(2));
    }

}
