package es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketBallEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketMatchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BasketMatchRepository extends MongoRepository<BasketMatchEntity, String> {
    Optional<BasketMatchEntity> findByMatchId(Integer matchId);
    List<BasketMatchEntity> findByMatchIdIn(List<Integer> idList);
    Optional<BasketMatchEntity> deleteByMatchId(Integer matchId);
}
