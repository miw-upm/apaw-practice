package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.ECommerceSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.CustomerEcommerceEntity;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ECommerceCustomerRepositoryIT {

    @Autowired
    private ECommerceCustomerRepository eCommerceCustomerRepository;

    @Autowired
    private ECommerceSeederService eCommerceSeederService;

    @BeforeEach
    void setUp() {
        eCommerceSeederService.deleteAll();
        eCommerceSeederService.seedDatabase();
    }

    @Test
    void testFindByUserNameNonExisting() {
        Optional<CustomerEcommerceEntity> customerEntity = eCommerceCustomerRepository.findByUserName("nonexistentUser");
        assertTrue(customerEntity.isEmpty(), "Customer should not exist for this username");
    }

    @Test
    void testFindByUserName() {
        Optional<CustomerEcommerceEntity> customerEntity = eCommerceCustomerRepository.findByUserName("user1");
        assertTrue(customerEntity.isPresent(), "Customer should exist for this username");

        CustomerEcommerceEntity entity = customerEntity.get();
        assertNotNull(entity);

        CustomerECommerce customer = entity.toCustomer();

        assertEquals("user1@example.com", customer.getEmail(), "Customer email should match");
        assertEquals(12345, customer.getPostalCode(), "Postal code should match");
        assertEquals("user1", customer.getUserName(), "Username should match");
    }
}


