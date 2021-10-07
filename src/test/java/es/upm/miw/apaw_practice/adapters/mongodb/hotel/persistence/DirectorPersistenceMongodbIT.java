package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class DirectorPersistenceMongodbIT {

    @Autowired
    private DirectorPersistenceMongodb directorPersistence;

    @Test
    void testReadEmails() {
        assertEquals(List.of("test@email.com", "email@email.com", "director@email.com")
                , this.directorPersistence.readEmails().stream().map(Director::getEmail).collect(Collectors.toList()));
    }
}
