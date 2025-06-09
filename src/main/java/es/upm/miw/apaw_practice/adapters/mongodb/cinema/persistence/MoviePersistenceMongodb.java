package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.MovieRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.MovieEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
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
                .map(MovieEntity::toMovie)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return this.movieRepository.findByTitle(title)
                .map(MovieEntity::toMovie);
    }

    @Override
    public Optional<Movie> findById(String id) {
        return this.movieRepository.findById(id)
                .map(MovieEntity::toMovie);
    }

    @Override
    public Movie create(Movie movie) {
        MovieEntity entity = MovieEntity.fromMovie(movie);
        return this.movieRepository.save(entity).toMovie();
    }

    @Override
    public Movie update(String title, Movie movie) {
        MovieEntity entity = this.movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie title: " + title));
        entity.updateFromMovie(movie);
        return this.movieRepository.save(entity).toMovie();
    }

    @Override
    public void delete(String title) {
        MovieEntity entity = this.movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie title: " + title));
        this.movieRepository.delete(entity);
    }
}