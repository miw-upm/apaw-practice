package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScreeningService {

    private final ScreeningPersistence screeningPersistence;
    private final DirectorPersistence directorPersistence;
    private final MoviePersistence moviePersistence;

    @Autowired
    public ScreeningService(ScreeningPersistence screeningPersistence,
                            DirectorPersistence directorPersistence,
                            MoviePersistence moviePersistence) {
        this.screeningPersistence = screeningPersistence;
        this.directorPersistence = directorPersistence;
        this.moviePersistence = moviePersistence;
    }

    public List<Screening> findAll() {
        return screeningPersistence.findAll();
    }

    public Screening findById(String id) {
        return screeningPersistence.findById(id).orElse(null);
    }

    public Screening create(Screening screening) {
        return screeningPersistence.create(screening);
    }

    public Screening update(String id, Screening screening) {
        return screeningPersistence.update(id, screening);
    }

    public void delete(String id) {
        screeningPersistence.delete(id);
    }
status

    public List<Integer> findAvailableSeatsByDirectorStyle(String style) {
        // 1. Buscar directores con el 'style' dado
        List<Director> directors = directorPersistence.findAll().stream()
                .filter(director -> style.equals(director.getStyle()))
                .collect(Collectors.toList());
        if (directors.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. Obtener los IDs de los directores encontrados
        Set<String> directorIds = directors.stream()
                .map(Director::getId) // Se usa Director.getId() para enlazar con Movie.directorId
                .collect(Collectors.toSet());

        // 3. Buscar películas de esos directores (usando el directorId)
        List<Movie> movies = moviePersistence.findAll().stream()
                .filter(movie -> directorIds.contains(movie.getDirectorId()))
                .collect(Collectors.toList());
        if (movies.isEmpty()) {
            return Collections.emptyList();
        }

        // 4. Obtener los IDs de las películas encontradas
        Set<String> movieIds = movies.stream()
                .map(Movie::getId) // Se usa Movie.getId() para enlazar con Screening.movieId
                .collect(Collectors.toSet());

        // 5. Buscar proyecciones (screenings) de esas películas y devolver los números de sala (roomNumber)
        return screeningPersistence.findAll().stream()
                .filter(screening -> movieIds.contains(screening.getMovieId()))
                .map(Screening::getRoomNumber) // Se usa Screening.getRoomNumber()
                .collect(Collectors.toList());
    }
}