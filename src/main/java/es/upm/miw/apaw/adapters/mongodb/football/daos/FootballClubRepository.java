package es.upm.miw.apaw.adapters.mongodb.football.daos;

import es.upm.miw.apaw.adapters.mongodb.football.entities.FootballClubEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FootballClubRepository extends MongoRepository<FootballClubEntity, Long> {

    Optional<FootballClubEntity> findByName(String name);

    boolean existsByClubId(Long clubId);
}
