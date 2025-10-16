package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.persistenceports.football.FootballClubPersistence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballClubService {

    private final FootballClubPersistence clubPersistence;

    public FootballClubService(FootballClubPersistence clubPersistence) {
        this.clubPersistence = clubPersistence;
    }

    public List<FootballClub> readAll() {
        return this.clubPersistence.readAll();
    }

    public FootballClub readByName(String name) {
        return this.clubPersistence.findByName(name)
                .orElseThrow(() -> new NotFoundException("Football club name: " + name));
    }
}
