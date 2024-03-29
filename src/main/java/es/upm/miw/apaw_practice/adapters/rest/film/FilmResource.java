package es.upm.miw.apaw_practice.adapters.rest.film;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.film.Film;
import es.upm.miw.apaw_practice.domain.models.film.Review;
import es.upm.miw.apaw_practice.domain.services.film.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(FilmResource.FILMS)
public class FilmResource {
    static final String FILMS = "/film/films";

    static final String TITLE_ID = "/{title}";
    static final String REVIEWS = "/reviews";
    static final String SEARCH_RATING = "/search_rating";
    static final String SEARCH_COMMENT = "/search_comment";

    private final FilmService filmService;

    @Autowired
    public FilmResource(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(TITLE_ID)
    public Stream<Film> read(@PathVariable String title) {
        return this.filmService.read(title);
    }

    @PutMapping(TITLE_ID + REVIEWS)
    public Film updateReviews(@PathVariable String title, @RequestBody List<Review> reviewList) {
        return this.filmService.updateReviews(title, reviewList);
    }

    @GetMapping(SEARCH_RATING)
    public Double findRatingAverageByDirectorDni(@RequestParam String q) {
        String directorDni = new LexicalAnalyzer().extractWithAssure(q, "dni");
        return this.filmService.findRatingAverageByDirectorDni(directorDni);
    }

    @GetMapping(SEARCH_COMMENT)
    public Stream<String> findCommentsWithTrueRecommendationByGenreStyle(@RequestParam String q) {
        String genreStyle = new LexicalAnalyzer().extractWithAssure(q, "style");
        return this.filmService.findCommentsWithTrueRecommendationByGenreStyle(genreStyle);
    }

}
