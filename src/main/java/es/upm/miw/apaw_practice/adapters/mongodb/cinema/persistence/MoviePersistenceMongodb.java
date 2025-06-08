package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.MovieRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("moviePersistence")
public class MoviePersistenceMongodb implements MoviePersistence {

    private final MovieRepository movieRepository;

    @Autowired
    public MoviePersistenceMongodb(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAll()
                .stream()
                .map(MoviePersistenceMapper::toMovie)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return this.movieRepository.findAll().stream()
                .filter(entity -> entity.getTitle().equals(title))
                .findFirst()
                .map(MoviePersistenceMapper::toMovie);
    }

    @Override
    public Movie save(Movie movie) {
        MovieEntity entity = this.movieRepository.save(MoviePersistenceMapper.toEntity(movie));
        return MoviePersistenceMapper.toMovie(entity);
    }

    // Implementa los métodos requeridos por tu interfaz MoviePersistence
}