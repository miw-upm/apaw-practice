package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import es.upm.miw.apaw_practice.domain.services.cinema.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(FilmResource.FILMS)
public class FilmResource {
    static final String FILMS = "/cinema/films";

    static final String SEARCH = "/search";
    static final String BARCODE = "/{barcode}";

    private final FilmService filmService;

    @Autowired
    public FilmResource(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(SEARCH)
    public Stream<Film> findFilmsByScreenNumber(@RequestParam String q) {
        Integer number = new LexicalAnalyzer().extractWithAssure(q, "number", Integer::new);
        return this.filmService.findFilmsByScreenNumber(number);
    }

    @DeleteMapping(BARCODE)
    public void delete(@PathVariable String barcode) {
        this.filmService.delete(barcode);
    }

}
