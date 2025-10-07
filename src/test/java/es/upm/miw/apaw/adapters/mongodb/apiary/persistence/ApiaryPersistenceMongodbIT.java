/*package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

public class ApiaryPersistenceMongodbIT {
}

 */
package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.ApiarySeeder;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import es.upm.miw.apaw.domain.persistenceports.apiary.ApiaryPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApiaryPersistenceMongodbIT {

    @Autowired
    private ApiaryPersistence apiaryPersistence;

    @Autowired
    private ApiarySeeder apiarySeeder;

    @BeforeEach
    void setUp() {
        this.apiarySeeder.deleteAll();
        this.apiarySeeder.seedDatabase();
    }

    @Test
    void testFindByLocationFound() {
        // El método devuelve un Stream, lo convertimos a List
        List<Apiary> apiaries = this.apiaryPersistence.findByLocation("Burgos")
                .collect(Collectors.toList());

        assertThat(apiaries).isNotEmpty();
        assertThat(apiaries.get(0).getLocation()).isEqualTo("Burgos");
        assertThat(apiaries.get(0).getRega()).isEqualTo("REGA00001");
    }

    @Test
    void testFindByLocationNotFound() {
        // También aquí convertimos el Stream a List
        List<Apiary> apiaries = this.apiaryPersistence.findByLocation("Valencia")
                .collect(Collectors.toList());

        assertThat(apiaries).isEmpty();
    }
}
