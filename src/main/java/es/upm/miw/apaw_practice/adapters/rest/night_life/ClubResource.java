package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Club;
import es.upm.miw.apaw_practice.domain.services.night_life.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Stream;

@RestController
@RequestMapping(ClubResource.CLUBS)
public class ClubResource {
    static final String CLUBS = "/night-life/clubs";
    private final ClubService clubService;
    @Autowired
    public ClubResource(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping
    public Stream<Club> readAll() {
        return this.clubService.readAll();
    }

}
