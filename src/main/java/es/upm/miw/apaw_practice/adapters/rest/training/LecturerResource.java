package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import es.upm.miw.apaw_practice.domain.services.training.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LecturerResource.LECTURERS)
public class LecturerResource {
    static final String LECTURERS = "/training/lecturers";

    private final LecturerService lecturerService;

    @Autowired
    public LecturerResource(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @PostMapping
    public Lecturer create(@RequestBody Lecturer lecturer) {
        return this.lecturerService.create(lecturer);
    }
}
