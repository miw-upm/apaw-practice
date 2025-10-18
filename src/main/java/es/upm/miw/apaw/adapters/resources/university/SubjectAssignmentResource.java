package es.upm.miw.apaw.adapters.resources.university;

import es.upm.miw.apaw.domain.models.university.Lesson;
import es.upm.miw.apaw.domain.models.university.SubjectAssignmentCapacityUpdating;
import es.upm.miw.apaw.domain.models.university.UserMobileSearching;
import es.upm.miw.apaw.domain.services.university.SubjectAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(SubjectAssignmentResource.SUBJECT_ASSIGNMENTS)
public class SubjectAssignmentResource {
    public static final String SUBJECT_ASSIGNMENTS = "/university/subject-assignments";
    public static final String ID = "/{id}";
    public static final String LESSONS = "/lessons";
    public static final String USERS_MOBILE = "/users/mobile";

    private final SubjectAssignmentService subjectAssignmentService;

    @Autowired
    public SubjectAssignmentResource(SubjectAssignmentService subjectAssignmentService) {
        this.subjectAssignmentService = subjectAssignmentService;
    }

    @GetMapping(ID + LESSONS)
    public List<Lesson> getLessons(@PathVariable UUID id) {
        return this.subjectAssignmentService.getLessons(id);
    }

    @PatchMapping
    public void updateCapacities(@RequestBody List<SubjectAssignmentCapacityUpdating> subjectAssignmentCapacityUpdatingList) {
        this.subjectAssignmentService.updateCapacities(subjectAssignmentCapacityUpdatingList.stream());
    }

    @GetMapping(USERS_MOBILE)
    public UserMobileSearching findUniqueUsersMobilesByCapacity(@RequestParam Integer capacity) {
        return this.subjectAssignmentService.findUniqueUsersMobilesByCapacity(capacity);
    }
}
