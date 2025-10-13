package es.upm.miw.apaw.domain.persistenceports.fighters;

import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.Rating;

import java.util.UUID;

public interface FighterPersistence {
    Fighter readByNickname(String nickname);
    Rating createRating(String nickname, Rating rating);
    void deleteRating(String nickname, UUID id);
    Fighter updateWins(String nickname, Fighter wins);
}
