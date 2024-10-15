package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class     RoomRepositoryIT {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.roomRepository.findAll().stream()
                .anyMatch(roomEntity ->
                        "101".equals(roomEntity.getNum()) &&
                                roomEntity.getId() != null &&
                                roomEntity.getNumBeds() == 1 &&
                                !roomEntity.isOccupied()
                ));
    }
}
