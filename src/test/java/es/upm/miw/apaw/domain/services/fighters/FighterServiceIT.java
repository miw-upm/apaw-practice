package es.upm.miw.apaw.domain.services.fighters;

import es.upm.miw.apaw.adapters.mongodb.fighters.daos.FighterRepository;
import es.upm.miw.apaw.adapters.mongodb.fighters.entities.FighterEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class FighterServiceIT {

    @Autowired
    private FighterService fighterService;

    @Autowired
    private FighterRepository fighterRepository;

    @MockitoBean
    private UserRestClient userRestClient;


    private UserDto seededUser0() {
        UserDto u = new UserDto();
        u.setId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
        u.setMobile("666000660");
        u.setFirstName("user0");
        return u;
    }


    private Rating rating(Integer score, String comment, UserDto user) {
        Rating r = new Rating();
        r.setScore(score);
        r.setComment(comment);
        r.setUser(user);
        return r;
    }

    @Test
    void testReadByNickname_ok() {
        Fighter fighter = this.fighterService.readByNickname("Spider");
        assertThat(fighter.getNickname()).isEqualTo("Spider");
        assertThat(fighter.getCountry()).isEqualTo("Brazil");
        assertThat(fighter.getWins()).isEqualTo(34);
        assertThat(fighter.getLosses()).isEqualTo(11);
    }

    @Test
    void testReadByNickname_notFound() {
        assertThrows(NotFoundException.class, () -> this.fighterService.readByNickname("no-existe"));
    }

    @Test
    void testCreateRating_ok() {
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder()
                                .id(invocation.getArgument(0))
                                .mobile("123456789")
                                .firstName("mock")
                                .build()
                );

        String nickname = "Spider";
        UserDto user = seededUser0();
        Rating toCreate = rating(4, "Muy técnico", user);

        Rating created = this.fighterService.createRating(nickname, toCreate);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getScore()).isEqualTo(4);
        assertThat(created.getComment()).isEqualTo("Muy técnico");
        assertThat(created.getCreatedAt()).isNotNull();

        assertThat(created.getUser()).isNotNull();
        assertThat(created.getUser().getId()).isEqualTo(user.getId());
        assertThat(created.getUser().getMobile()).isEqualTo("123456789");
        assertThat(created.getUser().getFirstName()).isEqualTo("mock");
    }

    @Test
    void testCreateRating_invalidScore_low() {
        UserDto user = seededUser0();
        Rating toCreate = rating(-1, "bad", user);

        assertThrows(IllegalArgumentException.class,
                () -> this.fighterService.createRating("Spider", toCreate));
    }

    @Test
    void testCreateRating_invalidScore_high() {
        UserDto user = seededUser0();
        Rating toCreate = rating(6, "too high", user);

        assertThrows(IllegalArgumentException.class,
                () -> this.fighterService.createRating("Spider", toCreate));
    }

    @Test
    void testCreateRating_fighterNotFound() {
        UserDto user = seededUser0();
        Rating toCreate = rating(3, "No existe ese luchador", user);

        assertThrows(NotFoundException.class,
                () -> this.fighterService.createRating("no-existe", toCreate));
    }

    @Test
    void testDeleteRating() {
        String nickname = "Spider";
        UUID ratingId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100");

        fighterService.deleteRatings(nickname, ratingId);

        FighterEntity fighter = fighterRepository.findByNickname(nickname).orElseThrow();
        boolean exists = fighter.getRatingsEntities() != null &&
                fighter.getRatingsEntities().stream().anyMatch(r -> ratingId.equals(r.getId()));
        assertThat(exists).isFalse();
    }

    @Test
    void testDeleteRating_ratingNotFound() {
        String nickname = "The Dragon";
        UUID notExisting = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0999");

        assertThrows(NotFoundException.class,
                () -> fighterService.deleteRatings(nickname, notExisting));
    }

    @Test
    void testUpdateWins() {
        Fighter updated = this.fighterService.updateWins("Spider", 77);
        assertThat(updated.getWins()).isEqualTo(77);
        var fighter = this.fighterRepository.findByNickname("Spider")
                .orElseThrow(() -> new AssertionError("Spider no encontrado en BD"));
        assertThat(fighter.getWins()).isEqualTo(77);
    }

    @Test
    void testUpdateWinsNotFound() {
        assertThrows(NotFoundException.class, () -> this.fighterService.updateWins("NoExiste", 10));
    }

    @Test
    void testUpdateWinsBadValue() {
        assertThrows(IllegalArgumentException.class, () -> this.fighterService.updateWins("Spider", -5));
    }
}
