package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import es.upm.miw.apaw.domain.persistenceports.football.FootballClubPersistence;
import es.upm.miw.apaw.domain.persistenceports.football.FootballPlayerPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class FootballPlayerServiceIT {

    @Autowired
    private FootballPlayerService footballPlayerService;

    @MockitoBean
    private FootballClubPersistence clubPersistence;

    @MockitoBean
    private FootballPlayerPersistence playerPersistence;

    @MockitoBean
    private UserRestClient userRestClient;

    private final UUID SAMPLE_USER_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001");

    private UserDto sampleUserDto() {
        return UserDto.builder()
                .id(SAMPLE_USER_ID)
                .mobile("600123456")
                .firstName("Carlos")
                .build();
    }

    private FootballPlayer samplePlayer() {
        return FootballPlayer.builder()
                .nickname("Rafa")
                .goalsScored(10)
                .build();
    }

    private FootballClub sampleClub(FootballPlayer player) {
        return FootballClub.builder()
                .clubId(1L)
                .name("Salamanca FC")
                .userId(SAMPLE_USER_ID)
                .players(List.of(player))
                .build();
    }

    @Test
    void testGetMobilesByNickname_ok() {
        FootballPlayer player = this.samplePlayer();
        FootballClub club = this.sampleClub(player);
        UserDto userDto = this.sampleUserDto();

        BDDMockito.given(this.playerPersistence.findByNickname("Rafa"))
                .willReturn(Optional.of(player));

        BDDMockito.given(this.clubPersistence.readAll())
                .willReturn(List.of(club));

        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willReturn(userDto);

        List<String> mobiles = this.footballPlayerService.getMobilesByNickname("Rafa");

        assertThat(mobiles)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly("600123456");
    }


    @Test
    void testGetMobilesByNickname_notFound() {
        BDDMockito.given(this.playerPersistence.findByNickname("NoExiste"))
                .willReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> this.footballPlayerService.getMobilesByNickname("NoExiste"));
    }
}
