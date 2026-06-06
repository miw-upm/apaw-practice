package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.models.football.Stadium;
import es.upm.miw.apaw.domain.persistenceports.football.FootballClubPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class FootballClubServiceIT {

    @Autowired
    private FootballClubService footballClubService;

    @MockitoBean
    private FootballClubPersistence footballClubPersistence;

    @Test
    void testReadAll_ok() {
        Stadium stadium1 = Stadium.builder()
                .stadiumId(UUID.randomUUID())
                .officialName("Salamanca Stadium")
                .capacity(40000)
                .roof(true)
                .build();

        Stadium stadium2 = Stadium.builder()
                .stadiumId(UUID.randomUUID())
                .officialName("Madrid Arena")
                .capacity(65000)
                .roof(false)
                .build();

        FootballClub club1 = FootballClub.builder()
                .clubId(1L)
                .name("Salamanca FC")
                .budget(new BigDecimal("4500000"))
                .founded(LocalDate.of(1985, 6, 12))
                .stadium(stadium1)
                .build();

        FootballClub club2 = FootballClub.builder()
                .clubId(2L)
                .name("Madrid United")
                .budget(new BigDecimal("6200000"))
                .founded(LocalDate.of(1972, 9, 18))
                .stadium(stadium2)
                .build();

        BDDMockito.given(this.footballClubPersistence.readAll())
                .willReturn(List.of(club1, club2));

        List<FootballClub> clubs = this.footballClubService.readAll();

        assertThat(clubs)
                .hasSize(2)
                .extracting(FootballClub::getName)
                .containsExactly("Salamanca FC", "Madrid United");
    }

    @Test
    void testReadByName_ok() {
        Stadium stadium = Stadium.builder()
                .stadiumId(UUID.randomUUID())
                .officialName("Salamanca Stadium")
                .capacity(40000)
                .roof(true)
                .build();

        FootballClub club = FootballClub.builder()
                .clubId(1L)
                .name("Salamanca FC")
                .budget(new BigDecimal("4500000"))
                .founded(LocalDate.of(1985, 6, 12))
                .stadium(stadium)
                .build();

        BDDMockito.given(this.footballClubPersistence.findByName("Salamanca FC"))
                .willReturn(Optional.of(club));

        FootballClub result = this.footballClubService.readByName("Salamanca FC");

        assertThat(result.getName()).isEqualTo("Salamanca FC");
        assertThat(result.getStadium().getOfficialName()).isEqualTo("Salamanca Stadium");
    }

    @Test
    void testReadByName_notFound() {
        BDDMockito.given(this.footballClubPersistence.findByName("NoExiste"))
                .willReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> this.footballClubService.readByName("NoExiste"));
    }

    @Test
    void testPatchBudget_ok() {
        FootballClub club = FootballClub.builder()
                .clubId(1L)
                .name("Salamanca FC")
                .budget(new BigDecimal("4500000"))
                .founded(LocalDate.of(1985, 6, 12))
                .build();

        FootballClub updated = FootballClub.builder()
                .clubId(1L)
                .name("Salamanca FC")
                .budget(new BigDecimal("9999999"))
                .founded(LocalDate.of(1985, 6, 12))
                .build();

        BDDMockito.given(this.footballClubPersistence.findByClubId(1L)).willReturn(Optional.of(club));
        BDDMockito.given(this.footballClubPersistence.save(BDDMockito.any(FootballClub.class))).willReturn(updated);

        FootballClub result = this.footballClubService.patchBudget(1L, new BigDecimal("9999999"));

        assertThat(result.getBudget()).isEqualByComparingTo(new BigDecimal("9999999"));
    }

    @Test
    void testPatchBudget_notFound() {
        BDDMockito.given(this.footballClubPersistence.findByClubId(999L))
                .willReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> this.footballClubService.patchBudget(999L, new BigDecimal("12345")));
    }
}
