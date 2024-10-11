package es.upm.miw.apaw_practice.domain.models.night_life;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerTest {
    @Test
    void testFull() {
        Customer customer = Customer.builder()
                .name("Raul")
                .phone("123456789")
                .email("raul@example.es")
                .build();
        assertEquals("Raul", customer.getName());
        assertEquals("123456789", customer.getPhone());
        assertEquals("raul@example.es", customer.getEmail());
    }
    @Test
    void testPartial() {
        Customer customer = Customer.builder()
                .name("Ana")
                .phone("987654321")
                .build();
        assertEquals("Ana", customer.getName());
        assertEquals("987654321", customer.getPhone());
        assertNull(customer.getEmail());
    }
}
