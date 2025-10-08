package es.upm.miw.apaw.adapters.resources.university;

import es.upm.miw.apaw.domain.models.university.Subject;
import es.upm.miw.apaw.domain.services.university.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SubjectResource.SUBJECTS)
public class SubjectResource {
    public static final String SUBJECTS = "/subjects";
    private final SubjectService subjectService;

    @Autowired
    public SubjectResource(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject create(@Valid @RequestBody Subject subject) {
        return this.subjectService.create(subject);
    }
}
