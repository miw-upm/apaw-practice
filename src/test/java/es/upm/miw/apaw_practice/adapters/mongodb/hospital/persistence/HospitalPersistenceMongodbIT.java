package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class HospitalPersistenceMongodbIT {

    @Autowired
    private HospitalPersistenceMongodb hospitalPersistenceMongodb;

    @Test
    void testUpdateDescription() {
        List<Hospital> hospitals = this.hospitalPersistenceMongodb.findByAvailableRoomsGreaterThan(350)
                .collect(Collectors.toList());
        assertEquals("Kindred Hospital Los Angeles", hospitals.get(0).getName());
        assertEquals("W Slauson Ave", hospitals.get(0).getAddress());
        assertEquals(400, hospitals.get(0).getAvailableRooms());
    }
}
