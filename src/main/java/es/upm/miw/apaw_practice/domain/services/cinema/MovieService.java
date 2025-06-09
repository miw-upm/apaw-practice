package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service("movieServiceCinema")
public class MovieService {

    private final MoviePersistence moviePersistence;
    private final DirectorPersistence directorPersistence;
    private final ScreeningPersistence screeningPersistence;

    @Autowired
    public MovieService(MoviePersistence moviePersistence,
                        DirectorPersistence directorPersistence,
                        ScreeningPersistence screeningPersistence) {
        this.moviePersistence = moviePersistence;
        this.directorPersistence = directorPersistence;
        this.screeningPersistence = screeningPersistence;
    }

    // Métodos CRUD estándar:
    public List<Movie> findAll() {
        return moviePersistence.findAll();
    }

    public Movie findByTitle(String title) {
        return moviePersistence.findByTitle(title).orElse(null);
    }

    public Movie create(Movie movie) {
        return moviePersistence.create(movie);
    }

    public Movie update(String title, Movie movie) {
        return moviePersistence.update(title, movie);
    }

    public void delete(String title) {
        moviePersistence.delete(title);
    }

    // Búsqueda 2: Dado el title de una Movie, obtener el número de screenings en 3D donde el Director es mayor de 50 años
    public int count3DScreeningsByMovieWithDirectorOver50(String title) {
        // 1. Buscar la película por título
        Movie movie = moviePersistence.findByTitle(title).orElse(null);
        if (movie == null) {
            return 0;
        }

        // 2. Buscar el director y calcular la edad
        Optional<Director> directorOpt = directorPersistence.findByDni(movie.getDirectorId());
        if (directorOpt.isEmpty()) {
            return 0;
        }
        Director director = directorOpt.get();
        LocalDate birthdate = LocalDate.parse(director.getBirthdate());
        if (Period.between(birthdate, LocalDate.now()).getYears() <= 50) {
            return 0;
        }

        // 3. Contar screenings en 3D de esa película
        List<Screening> screenings = screeningPersistence.findAll();
        // Asegúrate de comparar el id de la película (o el campo correcto según tu modelo)
        return (int) screenings.stream()
                .filter(s -> s.getMovieId().equals(movie.getId()))
                .filter(Screening::getThreeDFormat)
                .count();
    }
}