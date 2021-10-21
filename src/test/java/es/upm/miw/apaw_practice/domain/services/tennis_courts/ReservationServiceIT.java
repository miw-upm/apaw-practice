package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ReservationServiceIT {

    @Autowired
    private ReservationService reservationService;

    private String stringDate;
    private String stringTime;

    @BeforeEach
    void beforeEach(){
        stringDate = "19:10:21";
        stringTime = "11:00";
    }

    @Test
    void testExtractValuesFromString(){
        int[] expectedValues = {19,10,21};
        assertEquals(expectedValues.length, ReservationService.extractValuesFromString(stringDate).length);
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], ReservationService.extractValuesFromString(stringDate)[i]);
        }
    }

    @Test
    void testExtractDateFromString(){
        LocalDateTime expectedDate = LocalDateTime.of(2021, 10, 19, 11, 0);
        assertEquals(expectedDate, ReservationService.extractDateFromString(stringDate, stringTime));
    }
}
