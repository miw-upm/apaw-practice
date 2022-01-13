package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import es.upm.miw.apaw_practice.domain.services.training.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(LecturerResource.LECTURERS)
public class LecturerResource {
    static final String LECTURERS = "/training/lecturers";

    static final String DNI = "/{dni}";

    private final LecturerService lecturerService;

    @Autowired
    public LecturerResource(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @PutMapping(DNI)
    public void updateExperience(@PathVariable String dni, @RequestBody Lecturer lecturer) {
        this.lecturerService.updateExperience(dni, lecturer);
    }
}
