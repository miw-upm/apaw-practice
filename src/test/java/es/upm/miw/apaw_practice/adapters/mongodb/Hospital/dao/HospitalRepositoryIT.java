package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class HospitalRepositoryIT {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void testExistsByName() {

        HospitalEntity hospitalEntity = new HospitalEntity("Hospital Central", "Madrid", 300);
        hospitalRepository.save(hospitalEntity);

        boolean exists = hospitalRepository.existsByName("Hospital Central");

        assertTrue(exists);
    }
    @Test
    void testHospitalCreation() {
        // Correct constructor usage with String ID and empty lists
        HospitalEntity hospital = new HospitalEntity("1", "Hospital General", "123 Main St", 200, Collections.emptyList(), Collections.emptyList());
        hospitalRepository.save(hospital);

        assertTrue(hospitalRepository.existsByName("Hospital General"));
    }

    @Test
    void testSaveAndFindHospitalById() {
        HospitalEntity hospitalEntity = new HospitalEntity("Hospital Norte", "Barcelona", 400);
        hospitalEntity = hospitalRepository.save(hospitalEntity);
        Optional<HospitalEntity> foundHospital = hospitalRepository.findById(hospitalEntity.getId());
        assertTrue(foundHospital.isPresent());
        assertEquals("Hospital Norte", foundHospital.get().getName());
        assertEquals("Barcelona", foundHospital.get().getLocation());
        assertEquals(400, foundHospital.get().getCapacity());
    }
}
