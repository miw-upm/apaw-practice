package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class ClientPersistenceMongodbIT {

    @Autowired
    private ClientPersistenceMongodb clientPersistenceMongodb;

    @Test
    void testFindByDniNotFound() {
        assertThrows(NotFoundException.class, () -> this.clientPersistenceMongodb.findByDni("1"));
    }

    @Test
    void testFindByDni() {
        Client client = this.clientPersistenceMongodb.findByDni("11111111A");
        assertNotNull(client);
        assertEquals("Client1", client.getName());
        assertEquals("Client1", client.getSurname());
        assertEquals(111111111, client.getPhoneNumber());
        assertNotNull(client.getInvestmentFunds());
    }
}
