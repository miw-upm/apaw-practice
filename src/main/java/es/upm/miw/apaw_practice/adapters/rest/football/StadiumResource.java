package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import es.upm.miw.apaw_practice.domain.services.football.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StadiumResource.STADIUMS)
public class StadiumResource {

    static final String NAME_ID = "/{name}";
    static final String STADIUMS = "/stadiums";

    private final StadiumService stadiumService;

    @Autowired
    public StadiumResource(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @PutMapping(NAME_ID + STADIUMS)
    public void updateName(@PathVariable String name, @RequestBody Stadium stadium) {
        this.stadiumService.updateName(name, stadium);
    }
}
