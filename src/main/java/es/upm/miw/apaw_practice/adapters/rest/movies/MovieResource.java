package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.movies.Movie;
import es.upm.miw.apaw_practice.domain.services.movies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (movieService.existsImdbId(movie.getImdbId())) {
            throw new BadRequestException("IMDb ID '" + movie.getImdbId() + "' already exists.");
        }
        movieService.create(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
