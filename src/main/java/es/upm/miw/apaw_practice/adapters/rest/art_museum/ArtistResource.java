package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.services.art_museum.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(ArtistResource.ARTISTS)
public class ArtistResource {
    static final String ARTISTS = "/art-museum/artists";
    static final String SEARCH = "/search";

    private final ArtistService artistService;

    @Autowired
    public ArtistResource(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public Artist create(@RequestBody Artist artist) {
        return this.artistService.create(artist);
    }

    @GetMapping(SEARCH)
    public Stream<String> findByExhibitionNameDistinctArtStyles(@RequestParam String q) {
        String exhibitionName = new LexicalAnalyzer().extractWithAssure(q, "exhibitionName");
        return this.artistService.findByExhibitionNameDistinctArtStyles(exhibitionName);
    }
}
