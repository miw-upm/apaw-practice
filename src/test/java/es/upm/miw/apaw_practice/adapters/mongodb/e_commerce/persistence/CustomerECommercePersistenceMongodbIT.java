package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.e_commerce_model.CustomerECommerce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CustomerECommercePersistenceMongodbIT {

    @Autowired
    private CustomerECommercePersistenceMongodb customerECommercePersistenceMongodb;

    @Test
    void testFindByUserNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.customerECommercePersistenceMongodb.findByUserName("NonExistentUser"));
    }

    @Test
    void testFindByUserName() {
        CustomerECommerce customer = this.customerECommercePersistenceMongodb.findByUserName("user1");
        assertNotNull(customer);
        assertEquals("user1", customer.getUserName());
        assertEquals("user1@example.com", customer.getEmail());
    }
}