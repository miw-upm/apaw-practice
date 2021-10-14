package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import es.upm.miw.apaw_practice.domain.services.university.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SubjectResource.SUBJECTS)
public class SubjectResource {

    static final String SUBJECTS = "/university/subjects";
    static final String REFERENCE_ID = "/{reference}";
    static final String CLASSROOM = "/classroom";

    private final SubjectService subjectService;

    @Autowired
    public SubjectResource(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PutMapping(REFERENCE_ID + CLASSROOM)
    public void updateClassroom(@PathVariable Integer reference, @RequestBody Classroom classroom) {
        this.subjectService.updateClassroom(reference, classroom);
    }

    @PatchMapping(REFERENCE_ID)
    public void updateCredits(@PathVariable Integer reference, @RequestBody Integer credits) {
        this.subjectService.updateCredits(reference, credits);
    }
}
