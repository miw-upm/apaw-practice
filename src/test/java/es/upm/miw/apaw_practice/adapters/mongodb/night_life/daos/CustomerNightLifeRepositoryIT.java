package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import es.upm.miw.apaw_practice.TestConfig;

@TestConfig
public class CustomerNightLifeRepositoryIT {

    @Autowired
    private CustomerNightLifeRepository customerNightLifeRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.customerNightLifeRepository.findAll().stream()
                .anyMatch(cart ->
                        "Raul".equals(cart.getName()) &&
                                "33445566".equals(cart.getPhone()) &&
                                cart.getId() != null &&
                                "raul@upm.es".equals(cart.getEmail())
                ));
    }
}