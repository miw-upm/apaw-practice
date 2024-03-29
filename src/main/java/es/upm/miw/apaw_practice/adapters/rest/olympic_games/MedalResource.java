package es.upm.miw.apaw_practice.adapters.rest.olympic_games;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.olympic_games.Competitor;
import es.upm.miw.apaw_practice.domain.models.olympic_games.Medal;
import es.upm.miw.apaw_practice.domain.services.olympic_games.MedalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MedalResource.MEDAL)
public class MedalResource {

    static final String MEDAL = "/olympic-games/medals";
    static final String MEDAL_ID = "/{medalID}";
    static final String WINNER  = "/winner";
    static final String SEARCH = "/search";
    private final MedalService medalService;

    @Autowired
    public MedalResource(MedalService medalService) {
        this.medalService = medalService;
    }

    @PutMapping(MEDAL_ID + WINNER)
    public Medal updateWinner(@PathVariable String medalID, @RequestBody Competitor winner) {
        return this.medalService.updateWinner(medalID,winner);
    }

    @GetMapping(SEARCH)
    public List<String> findMedalTiersBySummerGamesAndAge(@RequestParam String q) {
        Boolean summerGames = Boolean.valueOf(new LexicalAnalyzer().extractWithAssure(q, "summerGames"));
        return this.medalService.findMedalTiersBySummerGamesAndAge(summerGames);
    }
}
