package es.upm.miw.apaw_practice.domain.models.emarketer;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@TestConfig
public class CustomerBuilderTest {

    @Test
    void testCustomer() {

        Customer customer = Customer.builder().name("Pedro")
                .address("Cuenca")
                .type("particular")
                .build();

        Assertions.assertEquals("Pedro", customer.getName());
        Assertions.assertEquals("Cuenca", customer.getAddress());
        Assertions.assertEquals("particular", customer.getType());
    }

}
