package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.FighterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FighterRepository extends MongoRepository<FighterEntity, String> {
    Optional<FighterEntity> findByNickname(String nickname);
    @Query(value = "{ 'ratingsEntities.comment': ?0 }")
    List<FighterEntity> findByRatingsEntitiesComment(String comment);
    @Query(value = "{ 'coach.academy': ?0 }")
    List<FighterEntity> findByCoachAcademy(String academy);
}