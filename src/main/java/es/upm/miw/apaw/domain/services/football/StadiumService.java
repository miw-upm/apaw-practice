package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import es.upm.miw.apaw.domain.models.football.Stadium;
import es.upm.miw.apaw.domain.persistenceports.football.FootballClubPersistence;
import es.upm.miw.apaw.domain.persistenceports.football.StadiumPersistence;
import org.springframework.stereotype.Service;

@Service
public class StadiumService {

    private final StadiumPersistence stadiumPersistence;
    private final FootballClubPersistence footballClubPersistence;

    public StadiumService(StadiumPersistence stadiumPersistence,
                          FootballClubPersistence footballClubPersistence) {
        this.stadiumPersistence = stadiumPersistence;
        this.footballClubPersistence = footballClubPersistence;
    }

    public Stadium create(Stadium stadium) {
        stadium.validate();
        if (this.stadiumPersistence.existsByOfficialName(stadium.getOfficialName())) {
            throw new ConflictException("Stadium already exists: " + stadium.getOfficialName());
        }
        return this.stadiumPersistence.save(stadium);
    }

    public Stadium readByOfficialName(String name) {
        return this.stadiumPersistence.findByOfficialName(name)
                .orElseThrow(() -> new NotFoundException("Stadium name: " + name));
    }

    public Stadium updateCapacity(String officialName, Integer newCapacity) {
        Stadium.builder().officialName(officialName).capacity(newCapacity).build().validate();

        Stadium existing = this.stadiumPersistence.findByOfficialName(officialName)
                .orElseThrow(() -> new NotFoundException("Stadium not found: " + officialName));

        existing.setCapacity(newCapacity);
        return this.stadiumPersistence.save(existing);
    }

    public void deleteByOfficialName(String officialName) {
        Stadium stadium = this.stadiumPersistence.findByOfficialName(officialName)
                .orElseThrow(() -> new NotFoundException("Stadium not found: " + officialName));

        this.stadiumPersistence.delete(stadium);
    }

    public Stadium update(String officialName, Stadium updated) {
        Stadium stadium = this.stadiumPersistence.findByOfficialName(officialName)
                .orElseThrow(() -> new NotFoundException("Stadium not found: " + officialName));

        stadium.setOfficialName(updated.getOfficialName());
        stadium.setCapacity(updated.getCapacity());
        stadium.setRoof(updated.getRoof());

        return this.stadiumPersistence.save(stadium);
    }

    public int getPlayersGoalsSum(String officialName) {
        Stadium stadium = this.stadiumPersistence.findByOfficialName(officialName)
                .orElseThrow(() -> new NotFoundException("Stadium not found: " + officialName));

        return this.footballClubPersistence.readAll().stream()
                .filter(club -> club.getStadium() != null &&
                        club.getStadium().getOfficialName().equals(stadium.getOfficialName()))
                .flatMap(club -> club.getPlayers() != null
                        ? club.getPlayers().stream()
                        : java.util.stream.Stream.<FootballPlayer>empty())
                .distinct()
                .mapToInt(FootballPlayer::getGoalsScored)
                .sum();
    }
}