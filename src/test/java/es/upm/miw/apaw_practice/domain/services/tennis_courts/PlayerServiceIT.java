package es.upm.miw.apaw_practice.domain.services.tennis_courts;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.CourtNumberList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class PlayerServiceIT {

    @Autowired
    private PlayerService playerService;

    @Test
    void testGet(){
        int[] expectedCourtNumbers = {2, 4};
        CourtNumberList result = this.playerService.getOccupiedCourts("Nacho");
        assertEquals(2, result.getNumbers().size());
        for (int i = 0; i < result.getNumbers().size(); i++) {
            assertEquals(expectedCourtNumbers[i], result.getNumbers().get(i));
        }

        assertThrows(NotFoundException.class, () -> this.playerService.getOccupiedCourts("Pepe"));
        assertThrows(NotFoundException.class, () -> this.playerService.getOccupiedCourts("Nadie"));
    }
}
