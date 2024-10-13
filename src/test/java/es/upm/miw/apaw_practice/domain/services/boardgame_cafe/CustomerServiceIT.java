package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.CustomerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestConfig
public class CustomerServiceIT {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerPersistence customerPersistence;

    @Test
    void testCreateCustomer() {
        Membership membership = new Membership("Gold", 6, new BigDecimal("3.0"));
        Customer customer = new Customer("test@email.com", "Test", LocalDate.of(1999, 9, 9), true, membership);
        when(customerPersistence.create(any(Customer.class))).thenReturn(customer);
        Customer createdCustomer = this.customerService.createCustomer(customer);
        assertNotNull(createdCustomer);
    }
}