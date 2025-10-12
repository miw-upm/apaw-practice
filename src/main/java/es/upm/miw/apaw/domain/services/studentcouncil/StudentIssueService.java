package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentIssuePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class StudentIssueService {

    private final StudentIssuePersistence studentIssuePersistence;

    @Autowired
    public StudentIssueService(StudentIssuePersistence studentIssuePersistence) {
        this.studentIssuePersistence = studentIssuePersistence;
    }

    public StudentIssue createStudentIssue(StudentIssue studentIssue) {
        if (studentIssue.getReplies() == null) {
            studentIssue.setReplies(new ArrayList<>());
        }
        studentIssue.setId(UUID.randomUUID());
        studentIssue.setReportDate(LocalDateTime.now());
        studentIssue.setClosed(false);
        return studentIssuePersistence.create(studentIssue);
    }
}