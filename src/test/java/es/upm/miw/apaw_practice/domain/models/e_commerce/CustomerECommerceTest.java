package es.upm.miw.apaw_practice.domain.models.e_commerce;

import es.upm.miw.apaw_practice.domain.models.e_commerce_model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerECommerceTest {

    private CustomerECommerceTree customerRoot;
    private CustomerECommerceTree customerLeaf;

    @BeforeEach
    void init() {
        ShoppingCartECommerce shoppingCartRoot = new ShoppingCartECommerce(1, LocalDateTime.now(), true, BigDecimal.valueOf(800.00), Collections.emptyList());
        List<ShippingAddress> shippingAddressesRoot = List.of(new ShippingAddress("C/Rey, 24", "Alice", "+123456789"));

        this.customerRoot = new CustomerECommerceComposite("user1", "user1@example.com",12345, shoppingCartRoot, shippingAddressesRoot);

        ShoppingCartECommerce shoppingCartLeaf = new ShoppingCartECommerce(10, LocalDateTime.now(), true, BigDecimal.valueOf(800.00),Collections.emptyList());
        List<ShippingAddress> shippingAddressesLeaf = List.of(new ShippingAddress("Address2", "City2", "PostalCode2"));

        CustomerECommerce customer = new CustomerECommerce("leafUser", "leafEmail", 54321, shoppingCartLeaf, shippingAddressesLeaf);
        this.customerLeaf = new CustomerECommerceLeaf(customer);

        this.customerRoot.add(this.customerLeaf);
    }

    @Test
    void testLeafCustomerIfComposite() {
        assertFalse(customerRoot.getCustomers().isEmpty());
        CustomerECommerceTree customerChild = customerRoot.getCustomers().get(0);
        assertFalse(customerChild.isComposite());
        assertEquals("leafUser", customerChild.getUserName());
        assertEquals("leafEmail", customerChild.getEmail());
        assertEquals(54321, customerChild.getPostalCode());
        assertFalse(customerChild.getShippingAddresses().isEmpty());
        assertEquals("Address2", customerChild.getShippingAddresses().get(0).getLocation());
    }

    @Test
    void testLeafCustomerIfLeaf() {
        assertTrue(customerLeaf.getCustomers().isEmpty());
    }

    @Test
    void testCustomerDetailsIfComposite() {
        assertTrue(customerRoot.isComposite());
        assertEquals("user1", customerRoot.getUserName());
        assertEquals("user1@example.com", customerRoot.getEmail());
        assertEquals(12345, customerRoot.getPostalCode());
        assertFalse(customerRoot.getShippingAddresses().isEmpty());
        assertEquals("C/Rey, 24", customerRoot.getShippingAddresses().get(0).getLocation());
    }

    @Test
    void testAddLeaf_throwsUnsupportedOperationException() {
        CustomerECommerce newCustomer = new CustomerECommerce("user3", "user3@example.com", 11111, null, List.of());
        assertThrows(UnsupportedOperationException.class, () -> this.customerLeaf.add(new CustomerECommerceLeaf(newCustomer)));
    }
}