package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.services.gym.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(GymResource.GYMS)
public class GymResource {
    static final String GYMS = "/gym/gym";
    static final String ADDRESS = "/{address}";
    static final String CELLPHONE = "/cellphone";
    static final String SEARCH = "/{label}";


    private final GymService gymService;

    @Autowired
    public GymResource(GymService gymService) {
        this.gymService = gymService;
    }

    @PutMapping(GymResource.ADDRESS + GymResource.CELLPHONE)
    public Gym updateCellPhone(@PathVariable String address, @RequestBody Gym gym) {
        return this.gymService.updateCellphone(address, gym);
    }

    @GetMapping(GymResource.SEARCH)
    public List<Athlete> findAthleteByGymLabel(@PathVariable String label) {
        return this.gymService.findAthleteByGymLabel(label);

    }


}
