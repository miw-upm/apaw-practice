package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestConfig
class ClientRepositoryIT {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindByDni(){
        assertTrue(this.clientRepository.findByDni("42279207D").isPresent());
        ClientEntity client = this.clientRepository.findByDni("42279207D").get();
        assertEquals("Jose",client.getName());
        assertEquals(LocalDate.of(2021,10,7),client.getRegistrationDate());
        assertEquals(1,client.getTable().getNumber());
        assertEquals(4, client.getWaiters().size());
    }
}
