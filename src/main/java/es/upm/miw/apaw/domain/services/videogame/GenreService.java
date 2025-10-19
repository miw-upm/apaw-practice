package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.models.videogame.Genre;
import es.upm.miw.apaw.domain.persistenceports.videogame.GenrePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenrePersistence genrePersistence;

    @Autowired
    public GenreService(GenrePersistence genrePersistence) {
        this.genrePersistence = genrePersistence;
    }

    public Genre updateAgeRestriction(String type, Integer newAge) {
        Genre genre = this.genrePersistence.findByType(type);
        genre.setAgeRestriction(newAge);
        return this.genrePersistence.update(genre);
    }
}
