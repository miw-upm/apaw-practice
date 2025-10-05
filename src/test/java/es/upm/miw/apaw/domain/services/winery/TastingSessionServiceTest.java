package es.upm.miw.apaw.domain.services.winery;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.winery.Evaluation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.models.winery.Wine;
import es.upm.miw.apaw.domain.persistenceports.winery.TastingSessionPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
public class TastingSessionServiceTest {

    @Autowired
    private TastingSessionService tastingSessionService;

    @MockitoBean
    private TastingSessionPersistence tastingSessionPersistence;

    @Test
    void testRead() {
        UUID sessionId = UUID.randomUUID();

        List<Wine> wineList = List.of(
                new Wine(UUID.randomUUID(), "Merlot", 2018, 13.5, new BigDecimal("25.50")),
                new Wine(UUID.randomUUID(), "Cabernet", 2020, 14.0, new BigDecimal("30.00"))
        );

        List<Evaluation> evaluationList = List.of(
                new Evaluation(10, "Really good organized", true),
                new Evaluation(3, "Bad place and worse wine", false)
        );

        TastingSession tastingSession = TastingSession.builder()
                .id(sessionId)
                .date(LocalDate.now().plusDays(1))
                .capacity(50)
                .location("Wine Cellar Madrid")
                .wines(wineList)
                .evaluations(evaluationList)
                .build();

        BDDMockito.given(this.tastingSessionPersistence.readById(sessionId))
                .willReturn(tastingSession);

        TastingSession retrieved = this.tastingSessionService.read(sessionId);

        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getId()).isEqualTo(sessionId);
        assertThat(retrieved.getDate()).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(retrieved.getCapacity()).isEqualTo(50);
        assertThat(retrieved.getLocation()).isEqualTo("Wine Cellar Madrid");

        assertThat(retrieved.getWines()).hasSize(2);
        assertThat(retrieved.getEvaluations()).hasSize(2);

    }

    @Test
    void testReadNotFound() {
        UUID sessionId = UUID.randomUUID();

        BDDMockito.given(this.tastingSessionPersistence.readById(sessionId))
                .willThrow(new NotFoundException("TastingSession id: " + sessionId));

        assertThatThrownBy(() -> this.tastingSessionService.read(sessionId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining(sessionId.toString());
    }
}
