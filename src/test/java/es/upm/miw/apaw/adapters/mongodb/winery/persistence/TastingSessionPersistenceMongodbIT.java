package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.models.winery.Wine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class TastingSessionPersistenceMongodbIT {

    @Autowired
    private TastingSessionPersistenceMongodb tastingSessionPersistence;

    @Test
    void testReadById() {
        TastingSession tastingSession = this.tastingSessionPersistence.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"));
        assertThat(tastingSession.getCapacity()).isEqualTo(20);
        assertThat(tastingSession.getDate().toString()).isEqualTo(LocalDate.now().plusDays(5).toString());
        assertThat(tastingSession.getLocation()).isEqualTo("Main Hall");
        assertThat(tastingSession.getWines())
                .extracting(Wine::getId)
                .containsExactlyInAnyOrder(
                        UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                        UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")
                );
        assertThat(tastingSession.getEvaluations()).isNotEmpty();
    }
}
