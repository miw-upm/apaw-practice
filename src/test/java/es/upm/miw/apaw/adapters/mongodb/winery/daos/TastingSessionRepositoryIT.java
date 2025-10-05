package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.TastingSessionEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.WineEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class TastingSessionRepositoryIT {

    @Autowired
    private TastingSessionRepository tastingSessionRepository;

    @Test
    void testRead() {
        assertTrue(this.tastingSessionRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100")).isPresent());
        TastingSessionEntity tastingSession = this.tastingSessionRepository.findById(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100")).get();
        assertThat(tastingSession.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"));
        assertThat(tastingSession.getCapacity()).isEqualTo(20);
        assertThat(tastingSession.getDate().toString()).isEqualTo(LocalDate.now().plusDays(5).toString());
        assertThat(tastingSession.getLocation()).isEqualTo("Main Hall");
        assertThat(tastingSession.getWineEntities())
                .extracting(WineEntity::getId)
                .containsExactlyInAnyOrder(
                        UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"),
                        UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002")
                );
        assertThat(tastingSession.getEvaluationEntities()).isNotEmpty();
    }
}
