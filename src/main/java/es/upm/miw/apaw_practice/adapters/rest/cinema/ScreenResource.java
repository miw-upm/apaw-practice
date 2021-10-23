package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import es.upm.miw.apaw_practice.domain.services.cinema.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ScreenResource.SCREENS)
public class ScreenResource {

    static final String SCREENS = "/cinema/screens";
    static final String NUMBER_ID = "/{number}";
    static final String ACTORS_NAME = "/films/actors/names";

    private final ScreenService screenService;

    @Autowired
    public ScreenResource(ScreenService screenService) {
        this.screenService = screenService;
    }

    @PutMapping(NUMBER_ID)
    public Screen update(@PathVariable Integer number, @RequestBody List<Spectator> spectatorList) {
        return this.screenService.updateSpectators(number, spectatorList);
    }

    @GetMapping(NUMBER_ID + ACTORS_NAME)
    public List<String> getActorsNameByScreenNumber(@PathVariable Integer number) {
        return this.screenService.getActorsNameByScreenNumber(number);
    }

}
