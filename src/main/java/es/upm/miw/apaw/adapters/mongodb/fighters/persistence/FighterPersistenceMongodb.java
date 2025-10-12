package es.upm.miw.apaw.adapters.mongodb.fighters.persistence;

import es.upm.miw.apaw.adapters.mongodb.fighters.daos.FighterRepository;
import es.upm.miw.apaw.adapters.mongodb.fighters.entities.FighterEntity;
import es.upm.miw.apaw.adapters.mongodb.fighters.entities.RatingEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import es.upm.miw.apaw.domain.persistenceports.fighters.FighterPersistence;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("fighterPersistence")
public class FighterPersistenceMongodb implements FighterPersistence {
    private final FighterRepository fighterRepository;

    public FighterPersistenceMongodb(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    @Override
    public Fighter readByNickname(String nickname) {
        FighterEntity entity = this.fighterRepository
                .findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("Fighter nickname: " + nickname));
        return entity.toFighter();
    }

    @Override
    public Rating createRating(String nickname, Rating rating) {
        FighterEntity fighter = this.fighterRepository
                .findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("Fighter nickname: " + nickname));

        if (rating.getCreatedAt() == null) {
            rating.setCreatedAt(LocalDateTime.now());
        }

        RatingEntity ratingEntity = new RatingEntity(rating);
        fighter.getRatingsEntities().add(ratingEntity);
        this.fighterRepository.save(fighter);

        return ratingEntity.toRating();
    }
}
