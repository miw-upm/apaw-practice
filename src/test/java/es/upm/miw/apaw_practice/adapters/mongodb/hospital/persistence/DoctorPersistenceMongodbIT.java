package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class DoctorPersistenceMongodbIT {

    @Autowired
    private DoctorPersistenceMongodb doctorPersistenceMongodb;

    @Test
    void testReadNicks(){
        assertEquals(3, (int) this.doctorPersistenceMongodb.readNicks().count());
    }
}
