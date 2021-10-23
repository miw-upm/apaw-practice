package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CourtServiceIT {

    @Autowired
    private CourtService courtService;

    @Test
    void testGet(){
        assertEquals(new BigDecimal("17.5"), this.courtService.get(2));
        assertEquals(BigDecimal.ZERO, this.courtService.get(4));

        assertThrows(NotFoundException.class, () -> this.courtService.get(8));
    }

}
