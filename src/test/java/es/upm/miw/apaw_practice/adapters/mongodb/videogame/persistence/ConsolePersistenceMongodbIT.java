package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ConsolePersistenceMongodbIT {

    @Autowired
    private ConsolePersistenceMongodb consolePersistence;

    @Test
    void findByConsoleReference(){
        assertEquals(1, this.consolePersistence.findByConsoleReference("Xbox").toList().size());
    }
}