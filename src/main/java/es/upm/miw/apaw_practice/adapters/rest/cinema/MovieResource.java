package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.services.cinema.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("movieResourceCinema")
@RequestMapping(MovieResource.MOVIES)
public class MovieResource {
    public static final String MOVIES = "/cinema/movies";

    private final MovieService movieService;

    @Autowired
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{title}")
    public Movie findByTitle(@PathVariable String title) {
        return movieService.findByTitle(title);
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie) {
        return movieService.create(movie);
    }

    @PutMapping("/{title}")
    public Movie update(@PathVariable String title, @RequestBody Movie movie) {
        return movieService.update(title, movie);
    }

    @DeleteMapping("/{title}")
    public void delete(@PathVariable String title) {
        movieService.delete(title);
    }
}