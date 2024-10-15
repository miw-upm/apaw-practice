package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.MovieRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.ActorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.AwardEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.MovieEntity;
import es.upm.miw.apaw_practice.domain.models.movies.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.MoviePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class MoviePersistenceMongodb implements MoviePersistence {

    private final MovieRepository movieRepository;

    @Autowired
    public MoviePersistenceMongodb(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void create(Movie movie) {
        MovieEntity movieEntity = new MovieEntity(
                movie.getImdbId(),
                movie.getTitle(),
                movie.getBoxOffice(),
                movie.getReleaseDate(),
                movie.getActorsFeaturing().stream()
                        .map(actor -> new ActorEntity(
                                actor.getArtisticName(),
                                actor.getRealName(),
                                actor.isAvailable(),
                                actor.getBirthDate()
                        ))
                        .collect(Collectors.toSet()),
                movie.getAwardWon() != null ? AwardEntity.fromAward(movie.getAwardWon()) : null
        );
        this.movieRepository.save(movieEntity);
    }

    @Override
    public boolean existsImdbId(String imdbId) {
        return this.movieRepository.findByImdbId(imdbId).isPresent();
    }
}
