package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CustomerPersistenceMongodbIT {

    @Autowired
    private CustomerPersistenceMongodb customerPersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.customerPersistence.read("0"));
    }

    @Test
    void testEmailNotExist() {
        assertFalse(this.customerPersistence.existEmail("0"));
    }

    @Test
    void testEmailExist() {
        assertTrue(this.customerPersistence.existEmail("joralvmel@gmail.com"));
    }

    @Test
    void testCreateAndRead() {
        Membership membership = new Membership("Gold", 6, new BigDecimal("3.0"));
        Customer customer = new Customer("omar123@gmail.com", "Omar", LocalDate.of(2000, 1, 1), true, membership);
        this.customerPersistence.create(customer);
        Customer customerBD = this.customerPersistence.read(customer.getEmail());
        assertEquals("omar123@gmail.com", customerBD.getEmail());
        assertEquals("Omar", customerBD.getName());
        assertEquals(LocalDate.of(2000, 1, 1), customerBD.getBirthDate());
        assertTrue(customerBD.isMember());
        assertEquals("Gold", customerBD.getMembership().getType());
    }

    @Test
    void testCreateAndUpdate() {
        Membership membership = new Membership("Gold", 6, new BigDecimal("3.0"));
        Customer customer = new Customer("pedro123@gmail.com", "Pedro", LocalDate.of(2000, 1, 1), true, membership);
        this.customerPersistence.create(customer);
        Customer customerBD = this.customerPersistence.read(customer.getEmail());
        assertEquals(customer, customerBD);
        assertEquals("pedro123@gmail.com", customerBD.getEmail());
        assertEquals("Pedro", customerBD.getName());
        assertEquals(LocalDate.of(2000, 1, 1), customerBD.getBirthDate());
        assertTrue(customerBD.isMember());
        assertEquals("Gold", customerBD.getMembership().getType());
        customer.setName("Nicolas");
        customer.setBirthDate(LocalDate.of(2001, 2, 2));
        customer.setMember(false);
        this.customerPersistence.update(customer.getEmail(), customer);
        customerBD = this.customerPersistence.read(customer.getEmail());
        assertEquals("Nicolas", customerBD.getName());
        assertEquals(LocalDate.of(2001, 2, 2), customerBD.getBirthDate());
        assertFalse(customerBD.isMember());
    }
}
