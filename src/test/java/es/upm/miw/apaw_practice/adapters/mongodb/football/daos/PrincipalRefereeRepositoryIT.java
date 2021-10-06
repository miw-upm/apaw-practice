package es.upm.miw.apaw_practice.adapters.mongodb.football.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class PrincipalRefereeRepositoryIT {
    @Autowired
    private PrincipalRefereeRepository principalRefereeRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.principalRefereeRepository.findAll().stream()
                .anyMatch(principalReferee ->
                        "Undiano".equals(principalReferee.getName()) &&
                                "Madrid".equals(principalReferee.getCityBorn()) &&
                                34 == principalReferee.getAge()
                ));
    }
}
