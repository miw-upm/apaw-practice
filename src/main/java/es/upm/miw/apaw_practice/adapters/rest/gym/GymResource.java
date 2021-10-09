package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.models.gym.Gym;
import es.upm.miw.apaw_practice.domain.services.gym.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GymResource.Gyms)
public class GymResource {
    static final String Gyms = "/gym/gym";
    static final String Search = "/search";
    private final GymService gymService;

    @Autowired
    public GymResource(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping(Search)
    public Gym findByLabel(@RequestParam String label) {
        return this.gymService.findByLabel(label);
    }

}
