package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.RenterRepository;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class RenterEntityTest {

    @Autowired
    RenterRepository renterRepository;

    @Test
    void testToRenter() {
        RenterEntity renterEntity = new RenterEntity();
        if (this.renterRepository.findByDni("51435421N").isPresent()) {
            renterEntity = this.renterRepository.findByDni("51435421N").get();
        }
        assertNotNull(renterEntity.getId());
        assertEquals("Pablo", renterEntity.getName());
        assertEquals("51435421N", renterEntity.getDni());
        assertNull(renterEntity.getLikedCar());

        Renter renter = renterEntity.toRenter();
        assertEquals("Pablo", renter.getName());
        assertEquals("51435421N", renter.getDni());
        assertNull(renter.getLikedCar());
    }
}
