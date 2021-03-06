package es.upm.miw.apaw_practice.adapters.rest.videoclub;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.videoclub.FilmCategory;
import es.upm.miw.apaw_practice.domain.models.videoclub.FilmCategoryAdultUpdating;
import es.upm.miw.apaw_practice.domain.services.videoclub.FilmCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(FilmCategoryResource.FILMS_CATEGORIES)
public class FilmCategoryResource {
    static final String FILMS_CATEGORIES = "/videoclub/categories";
    static final String PLUS_18 = "/adult";
    static final String REF_REF = "/{reference}";
    static final String SEARCH = "/search";

    private FilmCategoryService filmCategoryService;

    @Autowired
    public FilmCategoryResource(FilmCategoryService filmCategoryService) {
        this.filmCategoryService = filmCategoryService;
    }

    @PatchMapping(PLUS_18)
    public void updatePlus18(@RequestBody List<FilmCategoryAdultUpdating> filmCategoryAdultUpdatings) {
        this.filmCategoryService.updatePlus18(filmCategoryAdultUpdatings);
    }

    @GetMapping(REF_REF)
    public FilmCategory read(@PathVariable String reference) {
        return this.filmCategoryService.read(reference);
    }

    @GetMapping(SEARCH)
    public Stream<NameDto> findDistinctPlus18NameByFilmMaker(@RequestParam String q) {
        String filmMakerGender = new LexicalAnalyzer().extractWithAssure(q, "gender");
        return this.filmCategoryService.findDistinctPlus18NameByFilmMaker(filmMakerGender)
                .map(NameDto::new);
    }
}
