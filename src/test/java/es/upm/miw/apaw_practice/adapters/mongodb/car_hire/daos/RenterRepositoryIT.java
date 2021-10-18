package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class RenterRepositoryIT {

    @Autowired
    private RenterRepository renterRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.renterRepository.findByDni("51435421N").isPresent());
        RenterEntity renter = this.renterRepository.findByDni("51435421N").get();
        assertEquals("Pablo", renter.getName());
        assertNull(renter.getLikedCar());
    }
}
