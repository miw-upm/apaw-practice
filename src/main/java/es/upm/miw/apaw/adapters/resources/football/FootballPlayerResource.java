package es.upm.miw.apaw.adapters.resources.football;

import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import es.upm.miw.apaw.domain.services.football.FootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FootballPlayerResource.PLAYERS)
public class FootballPlayerResource {

    public static final String PLAYERS = "/football/players";
    public static final String NICKNAME_ID = "/{nickname}";

    private final FootballPlayerService footballPlayerService;

    public record MobilesDto(List<String> mobiles) {
    }

    @Autowired
    public FootballPlayerResource(FootballPlayerService footballPlayerService) {
        this.footballPlayerService = footballPlayerService;
    }

    @GetMapping(NICKNAME_ID)
    public FootballPlayer readByNickname(@PathVariable String nickname) {
        return this.footballPlayerService.readByNickname(nickname);
    }

    @GetMapping(NICKNAME_ID + "/mobiles")
    public MobilesDto getMobilesByNickname(@PathVariable String nickname) {
        return new MobilesDto(this.footballPlayerService.getMobilesByNickname(nickname));
    }
}
