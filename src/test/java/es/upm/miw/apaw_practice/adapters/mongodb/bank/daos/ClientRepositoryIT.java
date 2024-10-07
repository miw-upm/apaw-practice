package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ClientRepositoryIT {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.clientRepository.findByDni("11111111A").isPresent());
        ClientEntity client = this.clientRepository.findByDni("11111111A").get();
        assertEquals("Client1", client.getName());
        assertEquals("Client1", client.getSurname());
        assertEquals(111111111, client.getPhoneNumber());
        assertNotNull(client.getInvestmentFunds());
    }

}
