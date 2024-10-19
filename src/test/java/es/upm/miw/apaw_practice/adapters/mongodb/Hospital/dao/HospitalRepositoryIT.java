package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import org.junit.jupiter.api.Test;
import es.upm.miw.apaw_practice.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class HospitalRepositoryIT {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void testHospitalCreation() {
        HospitalEntity hospital = new HospitalEntity(
                "1",
                "Hospital General",
                "123 Main St",
                200, // Integer for capacity
                Collections.emptyList(),
                Collections.emptyList()
        );

        hospitalRepository.save(hospital);

        assertTrue(hospitalRepository.existsByName("Hospital General"));
    }
}
