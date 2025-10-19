package es.upm.miw.apaw.domain.services.football;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import es.upm.miw.apaw.domain.persistenceports.football.FootballClubPersistence;
import es.upm.miw.apaw.domain.persistenceports.football.FootballPlayerPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballPlayerService {

    private final FootballClubPersistence clubPersistence;
    private final FootballPlayerPersistence playerPersistence;
    private final UserRestClient userRestClient;

    public FootballPlayerService(FootballClubPersistence clubPersistence,
                                 FootballPlayerPersistence playerPersistence,
                                 UserRestClient userRestClient) {
        this.clubPersistence = clubPersistence;
        this.playerPersistence = playerPersistence;
        this.userRestClient = userRestClient;
    }

    public List<String> getMobilesByNickname(String nickname) {
        FootballPlayer player = this.playerPersistence.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("Football player not found: " + nickname));

        List<FootballClub> clubs = this.clubPersistence.readAll().stream()
                .filter(club -> club.getPlayers().stream()
                        .anyMatch(p -> p.getNickname().equals(player.getNickname())))
                .toList();

        return clubs.stream()
                .map(club -> this.userRestClient.readById(club.getUserId()).getMobile())
                .distinct()
                .toList();
    }
    public FootballPlayer readByNickname(String nickname) {
        return this.playerPersistence.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("Football player not found: " + nickname));
    }
}
