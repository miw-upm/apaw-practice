package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.MovieEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import java.util.List;
import java.util.stream.Collectors;

public class MoviePersistenceMapper {

    public static Movie toMovie(MovieEntity entity) {
        return new Movie(
                entity.getTitle(),
                entity.getReleaseDate(),
                entity.getDescription(),
                entity.getScreenings() == null ? null :
                        entity.getScreenings().stream()
                                .map(ScreeningPersistenceMapper::toScreening)
                                .collect(Collectors.toList()),
                entity.getDirector() == null ? null :
                        DirectorPersistenceMapper.toDirector(entity.getDirector())
        );
    }

    public static MovieEntity toEntity(Movie movie) {
        return new MovieEntity(
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getDescription(),
                movie.getScreenings() == null ? null :
                        movie.getScreenings().stream()
                                .map(ScreeningPersistenceMapper::toEntity)
                                .collect(Collectors.toList()),
                movie.getDirector() == null ? null :
                        DirectorPersistenceMapper.toEntity(movie.getDirector())
        );
    }
}