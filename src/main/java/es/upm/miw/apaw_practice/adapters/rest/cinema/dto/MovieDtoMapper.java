package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import java.time.LocalDate;

public class MovieDtoMapper {
    private MovieDtoMapper() {}

    public static MovieDto toDto(Movie movie) {
        return new MovieDto(
                movie.getTitle(),
                movie.getReleaseDate().toString(),
                movie.getDescription()
        );
    }

    public static Movie toDomain(MovieDto dto) {
        return new Movie(
                dto.getTitle(),
                LocalDate.parse(dto.getReleaseDate()),
                dto.getDescription()
        );
    }
}