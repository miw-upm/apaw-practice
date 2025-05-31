package es.upm.miw.apaw_practice.domain.models.hotel;

import org.junit.jupiter.api.Test;
import es.upm.miw.apaw_practice.TestConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;


@TestConfig
public class HotelRoomTest {

    @Test
    public void testBuilder() {
        HotelRoom room = HotelRoom.builder()
                .number("909")
                .type("dual")
                .price(new BigDecimal("150.00"))
                .build();
        assertEquals("909", room.getNumber());
        assertEquals("dual", room.getType());
        assertEquals(new BigDecimal("150.00"), room.getPrice());
    }
}
