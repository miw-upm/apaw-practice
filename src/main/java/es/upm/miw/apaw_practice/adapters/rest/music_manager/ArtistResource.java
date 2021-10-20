package es.upm.miw.apaw_practice.adapters.rest.music_manager;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.music_manager.Artist;
import es.upm.miw.apaw_practice.domain.services.music_manager.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ArtistResource.ARTISTS)
class ArtistResource {
    static final String ARTISTS = "/music_manager/artists";
    static final String FULLNAME = "/{fullName}";
    static final String AGE = "/age";
    public static final String SUM = "/sum";

    ArtistService artistService;

    @Autowired
    public ArtistResource(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PutMapping(FULLNAME + AGE)
    public Artist updateAge(@PathVariable String fullName, @RequestBody Integer age) {
        return this.artistService.updateAge(fullName, age);
    }

    @GetMapping(AGE + SUM)
    public Integer getSumAgeBySongGenre(@RequestParam String q) {
       return this.artistService.findSumAgeBySongGenre(new LexicalAnalyzer().extractWithAssure(q, "genre"));
    }
}