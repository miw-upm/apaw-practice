package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class RoomTest {

    @Test
    void testRoomLeaf() {
        Accomodation room = new Room(
                "100",
                false,
                3,
                BigDecimal.valueOf(59.99),
                Collections.emptyList()
        );

        assertFalse(room.isComposite());
    }

    @Test
    void testSuiteComposite() {
        Accomodation room = new Room(
                "100",
                false,
                2,
                BigDecimal.valueOf(59.99),
                Collections.emptyList()
        );

        Accomodation room2 = new Room(
                "101",
                true,
                4,
                BigDecimal.valueOf(99.99),
                Collections.emptyList()
        );
        Accomodation suite = new Suite();
        suite.add(room);
        suite.add(room2);

        assertTrue(suite.isComposite());
        assertTrue(suite.getOccupied());
        assertEquals(BigDecimal.valueOf(159.98), suite.getPrice());
        assertEquals("100-101", suite.getNum());
        assertEquals(6, suite.getNumBeds());
    }
}
