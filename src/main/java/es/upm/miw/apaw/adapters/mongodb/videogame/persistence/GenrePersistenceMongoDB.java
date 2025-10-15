package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.videogame.daos.GenreRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.GenreEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.videogame.Genre;
import es.upm.miw.apaw.domain.persistenceports.videogame.GenrePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository()
public class GenrePersistenceMongoDB implements GenrePersistence {

    private final GenreRepository genreRepository;

    @Autowired
    public GenrePersistenceMongoDB(GenreRepository genreRepository){
        this.genreRepository =genreRepository;
    }

    @Override
    public Genre update(Genre genre){
        GenreEntity genreEntity = this.genreRepository
                .findByType(genre.getType())
                .orElseThrow(() -> new NotFoundException("Genre type:" + genre.getType()));
        genreEntity.setAgeRestriction(genre.getAgeRestriction());
        return this.genreRepository.save(genreEntity).toGenre();
    }

    @Override
    public Genre findByType(String type){
        return this.genreRepository
                .findByType(type)
                .orElseThrow(() -> new NotFoundException("Genre type:" + type))
                .toGenre();

    }
    @Override
    public Stream<Genre> readAll(){
        return this.genreRepository.findAll().stream().map(GenreEntity::toGenre);
    }

}
