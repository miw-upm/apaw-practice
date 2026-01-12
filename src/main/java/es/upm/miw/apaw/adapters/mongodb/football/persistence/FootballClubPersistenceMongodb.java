package es.upm.miw.apaw.adapters.mongodb.football.persistence;

import es.upm.miw.apaw.adapters.mongodb.football.daos.FootballClubRepository;
import es.upm.miw.apaw.adapters.mongodb.football.entities.FootballClubEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.persistenceports.football.FootballClubPersistence;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository("footballClubPersistence")
public class FootballClubPersistenceMongodb implements FootballClubPersistence {

    private final FootballClubRepository clubRepository;
    private static final String CLUB_ID = "Football club id: ";

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
        if (!this.clubRepository.existsByClubId(clubId)) {
            throw new NotFoundException(CLUB_ID + clubId);
        }
        this.clubRepository.deleteById(clubId);
    }

    @Override
    public FootballClub updateBudget(Long clubId, BigDecimal newBudget) {
        FootballClubEntity entity = this.clubRepository.findAll().stream()
                .filter(c -> c.getClubId().equals(clubId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(CLUB_ID + clubId));

        entity.setBudget(newBudget);
        return this.clubRepository.save(entity).toFootballClub();
    }
}
