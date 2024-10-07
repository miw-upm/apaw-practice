package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.domain.services.university.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TeacherResource.TEACHERS)
public class TeacherResource {
    public static final String TEACHERS = "/teachers";

    public static final String NATIONAL_ID = "/{nationalId}";

    private final TeacherService teacherService;

    @Autowired
    public TeacherResource(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @DeleteMapping(NATIONAL_ID)
    public void delete(@PathVariable String nationalId) {
        teacherService.delete(nationalId);
    }
}
