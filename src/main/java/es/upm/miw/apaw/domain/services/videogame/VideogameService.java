package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideogameService {

    private final VideogamePersistence videogamePersistence;

    @Autowired
    public VideogameService(VideogamePersistence videogamePersistence) {
        this.videogamePersistence = videogamePersistence;
    }

    public void delete(String name) {
        this.videogamePersistence.delete(name);
    }

    public List<Videogame> getByGenre(String genreType) {
        return videogamePersistence.findByGenre(genreType);
    }

    public void setOnlineByGenre(String genreType, boolean online) {
        videogamePersistence.updateOnlineByGenre(genreType, online);
    }

}

