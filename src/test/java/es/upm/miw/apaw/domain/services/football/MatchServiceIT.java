package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.Match;
import es.upm.miw.apaw.domain.persistenceports.football.MatchPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class MatchServiceIT {

    @Autowired
    private MatchService matchService;

    @MockitoBean
    private MatchPersistence matchPersistence;

    @Test
    void testReadAll_ok() {
        Match match = Match.builder()
                .matchId(1L)
                .dateTime(LocalDateTime.now())
                .homeGoals(2)
                .awayGoals(1)
                .build();

        BDDMockito.given(this.matchPersistence.readAll()).willReturn(List.of(match));

        List<Match> result = this.matchService.readAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMatchId()).isEqualTo(1L);
    }

    @Test
    void testReadByMatchId_ok() {
        Match match = Match.builder()
                .matchId(1L)
                .homeGoals(2)
                .awayGoals(1)
                .build();

        BDDMockito.given(this.matchPersistence.findByMatchId(1L)).willReturn(Optional.of(match));

        Match result = this.matchService.readByMatchId(1L);

        assertThat(result.getMatchId()).isEqualTo(1L);
        assertThat(result.getHomeGoals()).isEqualTo(2);
    }

    @Test
    void testReadByMatchId_notFound() {
        BDDMockito.given(this.matchPersistence.findByMatchId(99L)).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.matchService.readByMatchId(99L));
    }
}