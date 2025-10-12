package es.upm.miw.apaw.adapters.resources.fighters;

import es.upm.miw.apaw.domain.models.fighters.Fighter;
import es.upm.miw.apaw.domain.models.fighters.Rating;
import es.upm.miw.apaw.domain.services.fighters.FighterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(FighterResource.FIGHTERS)
public class FighterResource {

    public static final String FIGHTERS = "/fighters";
    public static final String NICK_ID = "/{nickname}";
    public static final String RATINGS = "/ratings";

    private final FighterService fighterService;

    @Autowired
    public FighterResource(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @GetMapping(NICK_ID)
    public Fighter readByNickname(@PathVariable String nickname) {
        return this.fighterService.readByNickname(nickname);
    }

    @PostMapping(NICK_ID + RATINGS)
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createRating(@PathVariable String nickname,
                               @Valid @RequestBody Rating rating) {
        return this.fighterService.createRating(nickname, rating);
    }
}
