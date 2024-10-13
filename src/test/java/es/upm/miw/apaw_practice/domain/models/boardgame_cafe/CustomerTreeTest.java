package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTreeTest {

    private CustomerLeaf customerLeaf1;
    private CustomerLeaf customerLeaf2;
    private CustomerComposite customerComposite;

    @BeforeEach
    void setUp() {
        Membership membership = new Membership("Gold", 6, new BigDecimal("3.0"));

        Customer customer1 = new Customer("santiago@example.com", "Santiago", LocalDate.of(1990, 1, 1), true, membership);
        Customer customer2 = new Customer("fernando@example.com", "Fernando", LocalDate.of(1992, 2, 2), true, membership);

        customerLeaf1 = new CustomerLeaf(customer1);
        customerLeaf2 = new CustomerLeaf(customer2);

        customerComposite = new CustomerComposite("Group 1");
        customerComposite.add(customerLeaf1);
        customerComposite.add(customerLeaf2);
    }

    @Test
    void testCustomerLeaf() {
        assertEquals("santiago@example.com", customerLeaf1.getEmail());
        assertEquals("Santiago", customerLeaf1.getName());
        assertEquals(LocalDate.of(1990, 1, 1), customerLeaf1.getBirthDate());
        assertTrue(customerLeaf1.isMember());
        assertEquals("Gold", customerLeaf1.getMembership().getType());
        assertFalse(customerLeaf1.isComposite());
    }

    @Test
    void testCustomerComposite() {
        assertEquals("Group 1: Santiago, Fernando", customerComposite.getName());
        assertEquals("santiago@example.com, fernando@example.com", customerComposite.getEmail());
        assertTrue(customerComposite.isMember());
        assertTrue(customerComposite.isComposite());
    }

    @Test
    void testUnsupportedOperations() {
        assertThrows(UnsupportedOperationException.class, customerComposite::getBirthDate);
        assertThrows(UnsupportedOperationException.class, customerComposite::getMembership);
        assertThrows(UnsupportedOperationException.class, () -> customerLeaf1.add(customerLeaf2));
        assertThrows(UnsupportedOperationException.class, () -> customerLeaf1.remove(customerLeaf2));
    }
}