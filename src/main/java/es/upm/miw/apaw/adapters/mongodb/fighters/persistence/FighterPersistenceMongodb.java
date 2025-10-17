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
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("fighterPersistence")
public class FighterPersistenceMongodb implements FighterPersistence {
    private final FighterRepository fighterRepository;
    String fighterNickname = "Fighter nickname: ";

    public FighterPersistenceMongodb(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    @Override
    public Fighter readByNickname(String nickname) {
        FighterEntity entity = this.fighterRepository
                .findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException(fighterNickname + nickname));
        return entity.toFighter();
    }

    @Override
    public Rating createRating(String nickname, Rating rating) {
        FighterEntity fighter = this.fighterRepository
                .findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException(fighterNickname + nickname));

        if (rating.getCreatedAt() == null) {
            rating.setCreatedAt(LocalDateTime.now());
        }

        RatingEntity ratingEntity = new RatingEntity(rating);
        fighter.getRatingsEntities().add(ratingEntity);
        this.fighterRepository.save(fighter);

        return ratingEntity.toRating();
    }

    @Override
    public void deleteRating(String nickname, UUID ratingId) {
        FighterEntity fighter = this.fighterRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException(fighterNickname + nickname));
        List<RatingEntity> ratings = fighter.getRatingsEntities();
        if (ratings == null || ratings.isEmpty()) {
            this.fighterRepository.save(fighter);
            return;
        }
        ratings.removeIf(r -> ratingId.equals(r.getId()));
        fighter.setRatingsEntities(ratings);
        this.fighterRepository.save(fighter);
    }
    @Override
    public Fighter updateWins(String nickname, Fighter wins) {
        FighterEntity entity = this.fighterRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException(fighterNickname + nickname));
        entity.setWins(wins.getWins());
        return this.fighterRepository.save(entity).toFighter();
    }
    @Override
    public Stream<Fighter> findByRatingComment(String comment) {
        return this.fighterRepository.findByRatingsEntitiesComment(comment).stream()
                .map(FighterEntity::toFighter);
    }
    @Override
    public Stream<Fighter> findByCoachAcademy(String academy) {
        return this.fighterRepository.findByCoachAcademy(academy).stream()
                .map(FighterEntity::toFighter);
    }
}
