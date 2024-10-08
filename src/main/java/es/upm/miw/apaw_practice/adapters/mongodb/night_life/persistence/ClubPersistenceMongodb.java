package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.ClubRepository;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.ClubPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ClubEntity;

import java.util.stream.Stream;

@Repository("clubPersistence")
public class ClubPersistenceMongodb implements ClubPersistence {
    private final ClubRepository clubRepository;
    @Autowired
    public ClubPersistenceMongodb(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Stream<Club> readAll() {
        return this.clubRepository
                .findAll().stream()
                .map(ClubEntity::toClub);
    }
}
