package es.upm.miw.apaw.adapters.resources.university;

import es.upm.miw.apaw.domain.models.university.DurationSum;
import es.upm.miw.apaw.domain.models.university.Teacher;
import es.upm.miw.apaw.domain.services.university.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(TeacherResource.TEACHERS)
public class TeacherResource {
    public static final String TEACHERS = "/university/teachers";
    public static final String ID_ID = "/{id}";
    public static final String LESSONS_DURATION = "/lessons/duration";
    private final TeacherService teacherService;

    @Autowired
    public TeacherResource(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PutMapping(ID_ID)
    public Teacher update(@Valid @PathVariable UUID id, @Valid @RequestBody Teacher teacher) {
        return this.teacherService.update(id, teacher);
    }

    @GetMapping(LESSONS_DURATION)
    public DurationSum findLessonDurationSumByTeacherFullName(@RequestParam String fullName) {
        return this.teacherService.findLessonDurationSumByTeacherFullName(fullName);
    }
}
