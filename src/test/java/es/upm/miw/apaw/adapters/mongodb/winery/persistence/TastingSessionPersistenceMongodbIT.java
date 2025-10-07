package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.adapters.mongodb.winery.daos.WinerySeeder;
import es.upm.miw.apaw.domain.models.winery.Evaluation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.models.winery.Wine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class TastingSessionPersistenceMongodbIT {

    @Autowired
    private TastingSessionPersistenceMongodb tastingSessionPersistenceMongodb;

    @Autowired
    private WinerySeeder winerySeeder;

    @Test
    void testReadById() {
        TastingSession tastingSession = this.tastingSessionPersistenceMongodb.readById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"));
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

    @Test
    void testUpdate() {
        Optional<TastingSession> tastingSession = this.tastingSessionPersistenceMongodb.readAll()
                .filter(session -> UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100").equals(session.getId()))
                .findFirst();
        assertThat(tastingSession).isPresent();
        tastingSession.get().setEvaluations(List.of(new Evaluation(3, "Poor quality wines", false)));
        this.tastingSessionPersistenceMongodb.update(tastingSession.get());
        Optional<TastingSession> newTastingSession = this.tastingSessionPersistenceMongodb.readAll()
                .filter(session -> UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100").equals(session.getId()))
                .findFirst();
        assertThat(newTastingSession).isPresent();
        assertThat(newTastingSession.get().getEvaluations()).hasSize(1);
        assertThat(newTastingSession.get().getEvaluations().getFirst().getScore()).isEqualTo(3);
        winerySeeder.deleteAll();
        winerySeeder.seedDatabase();
    }
}
