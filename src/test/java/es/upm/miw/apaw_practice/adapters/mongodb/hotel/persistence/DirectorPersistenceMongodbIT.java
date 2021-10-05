package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class DirectorPersistenceMongodbIT {

    @Autowired
    private DirectorPersistenceMongodb directorPersistence;

    @Test
    void testReadEmails() {
        assertEquals(List.of("test@email.com",
                "email@email.com",
                "director@email.com"
        ), this.directorPersistence.readEmails());
    }
}
