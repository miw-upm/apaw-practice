package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.services.gym.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GymResource.Gyms)
public class GymResource {
    static final String Gyms = "/gym/gym";
    static final String Address = "/{address}";
    static final String cellphone = "/cellphone";


    private final GymService gymService;

    @Autowired
    public GymResource(GymService gymService) {
        this.gymService = gymService;
    }

    @PutMapping(GymResource.Address + GymResource.cellphone)
    public Gym updateCellPhone(@PathVariable String address, @RequestBody Gym gym) {
        return this.gymService.updateCellphone(address, gym);
    }


}
