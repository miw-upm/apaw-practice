package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final MoviePersistence moviePersistence;

    @Autowired
    public MovieService(MoviePersistence moviePersistence) {
        this.moviePersistence = moviePersistence;
    }

    /**
     * List all movies.
     */
    public List<Movie> findAll() {
        return moviePersistence.findAll();
    }

    /**
     * Find a movie by its title.
     * @throws NotFoundException if movie is not found
     */
    public Movie findByTitle(String title) {
        return moviePersistence.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie not found: " + title));
    }

    /**
     * Create a new movie.
     */
    public Movie create(Movie movie) {
        return moviePersistence.save(movie);
    }

    /**
     * Update all fields of a movie except its title.
     * @throws NotFoundException if movie is not found
     */
    public Movie update(String title, Movie newMovie) {
        Movie movie = this.findByTitle(title);
        // No actualices el título ya que es el identificador natural
        if (newMovie.getReleaseDate() != null) {
            movie.setReleaseDate(newMovie.getReleaseDate());
        }
        if (newMovie.getDescription() != null) {
            movie.setDescription(newMovie.getDescription());
        }
        // ...otros campos si los tienes
        return moviePersistence.save(movie);
    }

    /**
     * Delete a movie by its title.
     * @throws NotFoundException if movie is not found
     */
    public void delete(String title) {
        Movie movie = this.findByTitle(title);
        moviePersistence.delete(movie);
    }

    /**
     * Update selected fields of a movie (PATCH).
     * @throws NotFoundException if movie is not found
     */
    public Movie updatePartial(String title, Map<String, Object> updates) {
        Movie movie = this.findByTitle(title);
        updates.forEach((key, value) -> {
            switch (key) {
                case "description":
                    if (value != null) movie.setDescription((String) value);
                    break;
                // otros campos aquí
                default:
                    throw new IllegalArgumentException("Unknown field for patch: " + key);
            }
        });
        return moviePersistence.save(movie);
    }
}