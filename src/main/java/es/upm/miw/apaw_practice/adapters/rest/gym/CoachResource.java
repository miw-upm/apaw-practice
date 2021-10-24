package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.domain.services.gym.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CoachResource.GYMS)
public class CoachResource {
    static final String GYMS = "/gym/coach";
    static final String DNI = "/{dni}";

    private final CoachService coachService;

    @Autowired
    public CoachResource(CoachService coachService) {
        this.coachService = coachService;
    }


    @DeleteMapping(DNI)
    public void delete(@PathVariable String dni) {
        this.coachService.delete(dni);
    }
}
