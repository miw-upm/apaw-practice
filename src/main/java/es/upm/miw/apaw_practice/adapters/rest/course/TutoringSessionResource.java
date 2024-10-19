package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.domain.models.course.TutoringSession;
import es.upm.miw.apaw_practice.domain.services.course.TutoringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TutoringSessionResource.TUTORINGSESSIONS)
public class TutoringSessionResource {

    static final String TUTORINGSESSIONS = "/course/tutoringsessions";
    static final String TITTLE = "/{tittle}";


    private final TutoringSessionService tutoringSessionService;

    @Autowired
    public TutoringSessionResource(TutoringSessionService tutoringSessionService) {
        this.tutoringSessionService = tutoringSessionService;
    }

    @DeleteMapping(TITTLE)
    public void delete(@PathVariable String tittle){
        this.tutoringSessionService.delete(tittle);
    }
}
