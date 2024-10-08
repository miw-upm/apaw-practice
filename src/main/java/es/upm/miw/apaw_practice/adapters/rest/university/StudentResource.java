package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.services.university.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StudentResource.STUDENTS)
public class StudentResource {
    public static final String STUDENTS = "/students";

    public static final String EMAIL = "/{email}";


    private final StudentService studentService;

    @Autowired
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping(EMAIL)
    public void post(@PathVariable String email, @RequestBody Student student) {
        if (!studentService.existsEmail(email)) {
            throw new BadRequestException("No student found with email " + email);
        }
        studentService.update(email, student);
    }
}
