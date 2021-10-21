package es.upm.miw.apaw_practice.domain.services.game_wow;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class RaidServiceIT {
    @Autowired
    private RaidService raidService;

    @Test
    void testFindPlayerNumberAdditionBySpellPower(){
        assertEquals(10,this.raidService.findPlayerNumberAdditionBySpellPower(106));
    }
}
