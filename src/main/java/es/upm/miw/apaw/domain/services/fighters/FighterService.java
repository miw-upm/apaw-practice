package es.upm.miw.apaw.domain.services.fighters;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import es.upm.miw.apaw.domain.persistenceports.fighters.FighterPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FighterService {

    private final FighterPersistence fighterPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public FighterService(FighterPersistence fighterPersistence, UserRestClient userRestClient) {
        this.fighterPersistence = fighterPersistence;
        this.userRestClient = userRestClient;
    }

    public Fighter readByNickname(String nickname) {
        return this.fighterPersistence.readByNickname(nickname);
    }

    public Rating createRating(String nickname, Rating rating) {
        this.fighterPersistence.readByNickname(nickname);
        if (rating.getScore() < 0 || rating.getScore() > 5) {
            throw new IllegalArgumentException("score must be between 0 and 5");
        }

        rating.setCreatedAt(LocalDateTime.now());
        UserDto userDto = this.userRestClient.readById(rating.getUser().getId());
        rating.setUser(userDto);
        Rating ratingDb = this.fighterPersistence.createRating(nickname, rating);
        ratingDb.setUser(userDto);
        return ratingDb;
    }
}
