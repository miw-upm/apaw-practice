package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.PrincipalRefereeRepository;
import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class PrincipalRefereePersistenceMongodbIT {

    @Autowired
    private PrincipalRefereePersistenceMongodb principalRefereePersistenceMongodb;

    @Autowired
    private PrincipalRefereeRepository principalRefereeRepository;

    @Test
    void testCreateAndRead() {
        PrincipalReferee principalReferee = new PrincipalReferee("Merayo", "Ponferrada", 27);
        this.principalRefereePersistenceMongodb.create(principalReferee);
        assertTrue(this.principalRefereeRepository.findAll().stream()
                .anyMatch(principalRefereeBD ->
                        "Merayo".equals(principalRefereeBD.getName()) &&
                                "Ponferrada".equals(principalRefereeBD.getCityBorn()) &&
                                27 == principalRefereeBD.getAge()
                ));
    }
}
