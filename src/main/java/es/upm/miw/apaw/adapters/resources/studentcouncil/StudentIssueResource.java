package es.upm.miw.apaw.adapters.resources.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import es.upm.miw.apaw.domain.services.studentcouncil.StudentIssueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(StudentIssueResource.STUDENT_ISSUES)
public class StudentIssueResource {

    public static final String STUDENT_ISSUES = "student-issues";

    private final StudentIssueService studentIssueService;

    @Autowired
    public StudentIssueResource(StudentIssueService studentIssueService) {
        this.studentIssueService = studentIssueService;
    }

    @PostMapping
    public ResponseEntity<StudentIssue> createStudentIssue(@RequestBody @Valid StudentIssue studentIssue) {
        StudentIssue created = studentIssueService.createStudentIssue(studentIssue);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentIssue> updateStudentIssue(@PathVariable UUID id, @RequestBody @Valid StudentIssue studentIssue) {
        studentIssue.setId(id);
        StudentIssue updated = studentIssueService.updateStudentIssue(id, studentIssue);
        return ResponseEntity.ok(updated);
    }
}