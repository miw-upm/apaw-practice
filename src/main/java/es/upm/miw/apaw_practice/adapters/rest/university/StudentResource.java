package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import es.upm.miw.apaw_practice.domain.services.university.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(StudentResource.STUDENTS)
public class StudentResource {

    static final String STUDENTS = "/university/students";
    static final String SEARCH = "/search";

    private final StudentService studentService;

    @Autowired
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return this.studentService.create(student);
    }

    @GetMapping(SEARCH)
    public List<String> findDniListByClassroomSchool(@RequestParam String q) {
        String classroomSchool = new LexicalAnalyzer().extractWithAssure(q, "classroomSchool", String::new);
        return this.studentService.findDniListByClassroomSchool(classroomSchool);
    }
}
