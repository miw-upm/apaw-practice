package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.TestConfig;

import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.RidePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class RideServiceIT {

    @Autowired
    private RideService rideService;

    @Autowired
    private RidePersistence ridePersistence;

    @Test
    void testFindByThemeAndMaxCapacityLessThan() {
        assertTrue(this.rideService.findByThemeAndMaxCapacityLessThan("asdas", 10).toList().isEmpty());
        assertFalse(this.rideService.findByThemeAndMaxCapacityLessThan("Halloween", 55).toList().isEmpty());
        assertEquals(1, this.rideService.findByThemeAndMaxCapacityLessThan("Halloween", 55).toList().size());
    }

}
