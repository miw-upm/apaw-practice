package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.domain.services.course.TutoringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@RestController
@RequestMapping(TutoringSessionResource.TUTORINGSESSIONS)
public class TutoringSessionResource {

    static final String TUTORINGSESSIONS = "/course/tutoringsession";
    static final String TITTLE = "/{tittle}";
    static final String ROLE_PRICE = "/price/ofroleduration";


    private final TutoringSessionService tutoringSessionService;

    @Autowired
    public TutoringSessionResource(TutoringSessionService tutoringSessionService) {
        this.tutoringSessionService = tutoringSessionService;
    }

    @DeleteMapping(TITTLE)
    public void delete(@PathVariable String tittle){
        this.tutoringSessionService.delete(tittle);
    }

    @GetMapping(ROLE_PRICE)
    public BigDecimal priceSumOfRoleDuration(@RequestBody String role, @RequestBody LocalTime duration){
        return this.tutoringSessionService.priceSumOfRoleDuration(role, duration);
    }
}
