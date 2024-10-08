package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import es.upm.miw.apaw_practice.TestConfig;
@TestConfig
public class OwnerNightLifeRepositoryIT {
    @Autowired
    private OwnerNightLifeRepository ownerNightLifeRepository;
    @Test
    void testCreateAndRead() {
        assertTrue(this.ownerNightLifeRepository.findAll().stream()
                .anyMatch(cart ->
                        "Owner3".equals(cart.getName()) &&
                                "345678912".equals(cart.getPhone()) &&
                                cart.getId() != null &&
                                "owner3@example.com".equals(cart.getEmail())
                ));
    }

}

