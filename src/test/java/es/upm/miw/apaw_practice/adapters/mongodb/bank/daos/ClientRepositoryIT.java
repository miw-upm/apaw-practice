package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ClientRepositoryIT {

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

    @Test
    void testFindByDniIn() {
        assertNotNull(this.clientRepository.findByDniIn(List.of("11111111A", "22222222B")));
        List<ClientEntity> clients = this.clientRepository.findByDniIn(List.of("11111111A", "22222222B"));
        assertEquals("Client1", clients.get(0).getName());
        assertEquals("Client1", clients.get(0).getSurname());
        assertEquals(111111111, clients.get(0).getPhoneNumber());
        assertEquals("Client2", clients.get(1).getName());
        assertEquals("Client2", clients.get(1).getSurname());
        assertEquals(222222222, clients.get(1).getPhoneNumber());
        assertNotNull(clients.get(0).getInvestmentFunds());
        assertNotNull(clients.get(1).getInvestmentFunds());
    }

}
