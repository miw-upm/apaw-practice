package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;


@TestConfig
class ClientPersistenceMongodbIT {

    @Autowired
    private ClientPersistenceMongodb clientPersistence;

    @Test
    void testDniNoExist(){
        assertFalse(this.clientPersistence.existDni("00000000T"));
    }

    @Test
    void testReadAndDelete(){
        assertEquals("Maria",this.clientPersistence.readByDni("35935824A").getName());
        this.clientPersistence.delete("35935824A");
        assertTrue(this.clientPersistence.readAll().count() == 6);
    }
}
