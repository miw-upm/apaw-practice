package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

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
        assertEquals("manager",
                this.clientPersistence.readCategoryBySectionWaiterAndDniClient("64221329Q","dining room")
                        .collect(Collectors.toList()).get(0));
        this.clientPersistence.delete("35935824A");
        assertTrue(this.clientPersistence.readAll().count() == 6);
    }
}
