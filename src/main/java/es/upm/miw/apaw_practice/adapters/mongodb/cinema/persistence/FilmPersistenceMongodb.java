package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.FilmRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.FilmPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("filmPersistence")
public class FilmPersistenceMongodb implements FilmPersistence {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmPersistenceMongodb(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Stream<Film> readAll() {
        return this.filmRepository.findAll().stream()
                .map(FilmEntity::toFilm);
    }

    @Override
    public Stream<Film> findByScreenNumber(Integer number) {
        return this.filmRepository.findAll().stream()
                .map(FilmEntity::toFilm)
                .filter(film -> number.equals(film.getScreen().getNumber()));
    }

    @Override
    public void delete(String barcode) {
        this.filmRepository.deleteByBarcode(barcode);
    }
}

