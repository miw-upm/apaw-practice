package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerEntityTest {

    private List<Equipment> equipments;

    @BeforeEach
    void beforeEach(){
        this.equipments = new ArrayList<>();
        this.equipments.add(new Equipment("Ball", 3, new BigDecimal("2.5")));
        this.equipments.add(new Equipment("Racquet", 2, new BigDecimal("5")));
        this.equipments.add(new Equipment("Shoes", 2, new BigDecimal("4")));
    }

    @Test
    void testToEntityList(){
        EquipmentEntity[] expectedValues = {
                new EquipmentEntity("Ball",3, new BigDecimal("2.5")),
                new EquipmentEntity("Racquet",2, new BigDecimal("5")),
                new EquipmentEntity("Shoes",2, new BigDecimal("4"))
        };
        assertEquals(expectedValues[0], PlayerEntity.toEquipmentEntityList(equipments).get(0));
        assertEquals(expectedValues[1], PlayerEntity.toEquipmentEntityList(equipments).get(1));
        assertEquals(expectedValues[2], PlayerEntity.toEquipmentEntityList(equipments).get(2));
    }

    @Test
    void testToPlayer(){
        PlayerEntity playerEntity = new PlayerEntity("00000001T", "Juan", "Gomez", 26,
                PlayerEntity.toEquipmentEntityList(equipments));
        assertEquals(playerEntity.getDni(), playerEntity.toPlayer().getDNI());
        assertEquals(3, playerEntity.toPlayer().getEquipmentList().size());
    }

}
