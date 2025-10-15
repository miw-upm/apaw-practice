package es.upm.miw.apaw.adapters.resources.football;

import es.upm.miw.apaw.domain.models.football.Stadium;
import es.upm.miw.apaw.domain.services.football.StadiumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StadiumResource.STADIUMS)
public class StadiumResource {

    public static final String STADIUMS = "/football/stadiums";
    public static final String NAME_ID = "/{officialName}";

    private final StadiumService stadiumService;

    @Autowired
    public StadiumResource(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stadium create(@Valid @RequestBody Stadium stadium) {
        return this.stadiumService.create(stadium);
    }

    @GetMapping(NAME_ID)
    public Stadium readByOfficialName(@PathVariable String officialName) {
        return this.stadiumService.readByOfficialName(officialName);
    }
    @PatchMapping(NAME_ID)
    public Stadium updateCapacity(@PathVariable String officialName, @RequestBody Stadium stadium) {
        return this.stadiumService.updateCapacity(officialName, stadium.getCapacity());
    }

    @DeleteMapping(NAME_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByOfficialName(@PathVariable String officialName) {
        this.stadiumService.deleteByOfficialName(officialName);
    }

}
