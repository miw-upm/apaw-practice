package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe.PlaySessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaySessionService {

    private final PlaySessionPersistence playSessionPersistence;

    @Autowired
    public PlaySessionService(PlaySessionPersistence playSessionPersistence) {
        this.playSessionPersistence = playSessionPersistence;
    }

    public PlaySession updatePlaySessionGames(Integer playSessionId, List<Game> playSessionList) {
        PlaySession playSessionToUpdate = this.playSessionPersistence.read(playSessionId);
        playSessionToUpdate.setSelectedGames(playSessionList);
        return this.playSessionPersistence.update(playSessionId, playSessionToUpdate);
    }
}
