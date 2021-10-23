package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.Tennis_CourtsSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.CourtNumberList;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Equipment;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class PlayerPersistenceMongodbIT {

    @Autowired
    private PlayerPersistenceMongoDB playerPersistence;

    @Autowired
    private Tennis_CourtsSeederService tennis_courtsSeederService;

    @AfterEach
    void afterEach(){
        this.tennis_courtsSeederService.deleteAll();
        this.tennis_courtsSeederService.seedDatabase();
    }

    @Test
    void testCreateAndRead(){
        this.playerPersistence.create(new Player("0L", "Sonia", "Garza", 25));
        assertEquals("Sonia", this.playerPersistence.read("0L").getName());
        assertThrows(NotFoundException.class, () -> this.playerPersistence.read("none"));
    }

    @Test
    void testUpdateAndRead(){
        Equipment[] equipments = {
                new Equipment("Ball", 3, new BigDecimal("1.5")),
                new Equipment("Racquet", 2, new BigDecimal("5")),
                new Equipment("Shoes", 2, new BigDecimal("4"))
        };
        Player player;
        this.playerPersistence.updateEquipment("00000006R",List.of(equipments));
        player = this.playerPersistence.read("00000006R");
        assertEquals("Rob", player.getName());
        assertEquals(3, player.getEquipmentList().size());
    }

    @Test
    void testUpdateFail(){
        Equipment[] equipments = {
                new Equipment("Ball", 3, new BigDecimal("1.5")),
                new Equipment("Racquet", 2, new BigDecimal("5")),
                new Equipment("Shoes", 2, new BigDecimal("4"))
        };
        assertThrows(NotFoundException.class, () -> this.playerPersistence.updateEquipment("otro",List.of(equipments)));
    }

    @Test
    void testGet(){
        int[] expectedCourtNumbers = {2, 4};
        CourtNumberList result = this.playerPersistence.getOccupiedCourts("Nacho");
        assertEquals(2, result.getNumbers().size());
        for (int i = 0; i < result.getNumbers().size(); i++) {
            assertEquals(expectedCourtNumbers[i], result.getNumbers().get(i));
        }
        assertEquals(0, this.playerPersistence.getOccupiedCourts("Pepe").getNumbers().size());
    }
}
