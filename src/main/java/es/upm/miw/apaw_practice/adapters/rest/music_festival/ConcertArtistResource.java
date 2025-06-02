package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import es.upm.miw.apaw_practice.domain.services.music_festival.ConcertArtistService;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConcertArtistResource.ARTISTS)
public class ConcertArtistResource {

    static final String ARTISTS = "/music-festival/artists";
    static final String SEARCH = "/search";

    private final ConcertArtistService concertArtistService;

    @Autowired
    public ConcertArtistResource(ConcertArtistService concertArtistService) {
        this.concertArtistService = concertArtistService;
    }

    @GetMapping(SEARCH)
    public Stream<ConcertArtist> findByNationality(@RequestParam String q) {
        String nationality = new LexicalAnalyzer().extractWithAssure(q, "nationality");
        return this.concertArtistService.findByNationality(nationality);
    }
}