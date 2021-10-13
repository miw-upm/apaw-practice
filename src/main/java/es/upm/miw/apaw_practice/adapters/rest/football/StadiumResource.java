package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import es.upm.miw.apaw_practice.domain.services.football.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StadiumResource.STADIUMS)
public class StadiumResource {
    static final String STADIUMS = "/football/stadiums";

    static final String CITY_ID = "/{city}";
    static final String NAME = "/name";


    private final StadiumService stadiumService;

    @Autowired
    public StadiumResource(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @PutMapping(CITY_ID + NAME)
    public void updateName(@PathVariable String city, @RequestBody Stadium stadium) {
        this.stadiumService.updateName(city, stadium);
    }
}
