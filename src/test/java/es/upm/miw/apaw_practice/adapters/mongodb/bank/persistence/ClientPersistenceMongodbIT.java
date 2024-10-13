package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.BankSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class ClientPersistenceMongodbIT {

    @Autowired
    private ClientPersistenceMongodb clientPersistenceMongodb;
    @Autowired
    private BankSeederService bankSeederService;

    @Test
    void testFindByDniNotFound() {
        assertThrows(NotFoundException.class, () -> this.clientPersistenceMongodb.findByDni("1"));
    }

    @Test
    void testFindByDni() {
        Client client = this.clientPersistenceMongodb.findByDni("11111111A");
        assertEquals("Client1", client.getName());
        assertEquals("Client1", client.getSurname());
        assertEquals(111111111, client.getPhoneNumber());
    }

    @Test
    void testUpdateNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.clientPersistenceMongodb.updateName("1", "NewName"));
    }

    @Test
    void testUpdateName() {
        Client client = this.clientPersistenceMongodb.updateName("55555555E", "NewName");
        assertNotNull(client);
        assertEquals("NewName", client.getName());
        assertEquals("Client5", client.getSurname());
        assertEquals(555555555, client.getPhoneNumber());
        assertNotNull(client.getInvestmentFunds());
    }
}
