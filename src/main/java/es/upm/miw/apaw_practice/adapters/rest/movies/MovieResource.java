package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.movies.Movie;
import es.upm.miw.apaw_practice.domain.services.movies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping(MovieResource.MOVIES)
public class MovieResource {
    public static final String MOVIES = "/movies";

    private final MovieService movieService;

    @Autowired
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody Movie movie) {
        validateMovie(movie);
        if (movieService.existsImdbId(movie.getImdbId())) {
            throw new BadRequestException("IMDb ID '" + movie.getImdbId() + "' already exists.");
        }
        movieService.create(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void validateMovie(Movie movie) {
        if (movie.getImdbId() == null || movie.getImdbId().isBlank()) {
            throw new BadRequestException("IMDb ID cannot be null or empty.");
        }
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            throw new BadRequestException("Movie title cannot be null or empty.");
        }
        if (movie.getBoxOffice() == null || movie.getBoxOffice().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Movie box office must be greater than or equal to zero.");
        }
        if (movie.getReleaseDate() == null || movie.getReleaseDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Movie release date must be in the past or today.");
        }
    }
}
