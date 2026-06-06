package es.upm.miw.apaw.adapters.mongodb.football.persistence;

import es.upm.miw.apaw.adapters.mongodb.football.daos.FootballClubRepository;
import es.upm.miw.apaw.adapters.mongodb.football.entities.FootballClubEntity;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.persistenceports.football.FootballClubPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("footballClubPersistence")
public class FootballClubPersistenceMongodb implements FootballClubPersistence {

    private final FootballClubRepository clubRepository;

    public FootballClubPersistenceMongodb(FootballClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Optional<FootballClub> findByName(String name) {
        return this.clubRepository.findByName(name)
                .map(FootballClubEntity::toFootballClub);
    }

    @Override
    public List<FootballClub> readAll() {
        return this.clubRepository.findAll().stream()
                .map(FootballClubEntity::toFootballClub)
                .toList();
    }

    @Override
    public FootballClub save(FootballClub club) {
        return this.clubRepository.save(new FootballClubEntity(club)).toFootballClub();
    }

    @Override
    public void delete(Long clubId) {
        this.clubRepository.deleteById(clubId);
    }

    @Override
    public Optional<FootballClub> findByClubId(Long clubId) {
        return this.clubRepository.findById(clubId)
                .map(FootballClubEntity::toFootballClub);
    }
}