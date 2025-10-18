package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.GenreRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.daos.VideogameRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.VideogameEntity;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("videogamePersistence")
public class VideogamePersistenceMongoDB implements VideogamePersistence {

    @Autowired
    private final VideogameRepository videogameRepository;

    @Autowired
    private final GenreRepository genreRepository;


    @Autowired
    public VideogamePersistenceMongoDB(VideogameRepository videogameRepository,
                                       GenreRepository genreRepository) {
        this.videogameRepository = videogameRepository;
        this.genreRepository = genreRepository;


    }

    @Override
    public void delete(String name) {
        this.videogameRepository
                .deleteByName(name);

    }

    @Override
    public List<Videogame> findByGenre(String genreType) {
        GenreEntity genre = genreRepository.findByType(genreType)
                .orElseThrow(() -> new RuntimeException("Genre not found: " + genreType));

        return videogameRepository.findByGenreEntityId(genre.getId())
                .stream()
                .map(VideogameEntity::toVideogame)
                .toList();
    }

    @Override
    public void updateOnlineByGenre(String genreType, boolean online) {
        GenreEntity genre = genreRepository.findByType(genreType)
                .orElseThrow(() -> new RuntimeException("Genre not found: " + genreType));
        List<VideogameEntity> videogames = videogameRepository.findByGenreEntityId(genre.getId());
        videogames.forEach(v -> v.setOnline(online));
        videogameRepository.saveAll(videogames);
    }

}
