package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.videogame.Genre;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import jakarta.validation.Valid;
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

    public void delete(String name){
        this.videogamePersistence.delete(name);
    }

    public void updateOnlineByGenre(String genreName, Boolean online) {
        List<Videogame> videogames = this.videogamePersistence.findByGenre(genreName);
        videogames.forEach(v -> v.setOnline(online));
        this.videogamePersistence.saveAll(videogames);
    }

    }

