package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import es.upm.miw.apaw_practice.domain.services.game_wow.RaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RaidResource.GAMEWOW_RAIDS)
public class RaidResource {

    static final String GAMEWOW_RAIDS = "/gamewow/raids";
    static final String SEARCH = "/search";
    static final String ID_ID = "/{id}";
    static final String DIFICULTY = "/dificulty";

    private final RaidService raidService;

    @Autowired
    public RaidResource(RaidService raidService) {
        this.raidService = raidService;
    }

    @PutMapping(ID_ID + DIFICULTY)
    public Raid updateDificultyRaid(@PathVariable String id, @RequestBody String dificulty){
        return raidService.updateDificultyRaid(id,dificulty);
    }

    @GetMapping(SEARCH)
    public Integer findPlayerNumberAdditionBySpellPower(@RequestParam String q){
        Integer spellPower = new LexicalAnalyzer().extractWithAssure(q, "spellPower", Integer::new);
        return  raidService.findPlayerNumberAdditionBySpellPower(spellPower);
    }
}
