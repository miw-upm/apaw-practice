package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CustomerRepositoryIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindByEmail() {
        Optional<CustomerEntity> customerEntity = customerRepository.findByEmail("joralvmel@gmail.com");
        assertTrue(customerEntity.isPresent());
        assertNotNull(customerEntity.get());
        CustomerEntity customer = customerEntity.get();
        assertEquals("joralvmel@gmail.com", customer.getEmail());
        assertEquals("Jorge", customer.getName());
        assertEquals(LocalDate.of(1997, 9, 23), customer.getBirthDate());
        assertTrue(customer.isMember());
    }
}