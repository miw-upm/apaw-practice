package es.upm.miw.apaw.adapters.mongodb.fighters.persistence;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class FighterPersistenceMongodbIT {

    @Autowired
    private FighterPersistenceMongodb fighterPersistence;

    @Test
    void testReadByNickname_ok() {
        Fighter fighter = this.fighterPersistence.readByNickname("Spider");
        assertThat(fighter.getNickname()).isEqualTo("Spider");
        assertThat(fighter.getName()).isEqualTo("Anderson");
        assertThat(fighter.getLastName()).isEqualTo("Silva");
        assertThat(fighter.getCountry()).isEqualTo("Brazil");
        assertThat(fighter.getWins()).isEqualTo(34);
        assertThat(fighter.getLosses()).isEqualTo(11);
    }


    private UserDto seededUser0() {
        UserDto u = new UserDto();
        u.setId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
        u.setFirstName("user0");
        u.setMobile("666000660");
        return u;
    }
    @Test
    void testCreateRating_ok() {
        String nickname = "Spider";
        Rating rating = new Rating();
        rating.setScore(5);
        rating.setComment("Excelente peleador");
        rating.setUser(seededUser0());

        Rating saved = this.fighterPersistence.createRating(nickname, rating);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getScore()).isEqualTo(5);
        assertThat(saved.getComment()).isEqualTo("Excelente peleador");
        assertThat(saved.getCreatedAt()).isNotNull();

        Fighter fighter = this.fighterPersistence.readByNickname(nickname);
        assertThat(fighter.getRatings())
                .anyMatch(r -> r.getComment().equals("Excelente peleador") && r.getScore() == 5);
    }

    @Test
    void testCreateRating_fighterNotFound() {
        Rating rating = new Rating();
        rating.setScore(3);
        rating.setComment("ok");

        assertThrows(   NotFoundException.class,
                () -> this.fighterPersistence.createRating("no-existe", rating));
    }
}
