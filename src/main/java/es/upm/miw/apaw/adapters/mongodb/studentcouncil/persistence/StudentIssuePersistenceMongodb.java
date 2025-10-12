package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.StudentIssueRepository;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentIssueEntity;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentIssuePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class StudentIssuePersistenceMongodb implements StudentIssuePersistence {

    private final StudentIssueRepository repository;

    @Autowired
    public StudentIssuePersistenceMongodb(StudentIssueRepository repository) {
        this.repository = repository;
    }
    @Override
    public StudentIssue create(StudentIssue studentIssue) {
        StudentIssueEntity entity = StudentIssueEntity.builder()
                .id(studentIssue.getId())
                .statement(studentIssue.getStatement())
                .reportDate(studentIssue.getReportDate())
                .closed(studentIssue.getClosed())
                .urgency(studentIssue.getUrgency())
                .replies(new ArrayList<>())
                .build();
        repository.save(entity);

        studentIssue.setId(entity.getId());
        studentIssue.setReplies(new ArrayList<>());
        return studentIssue;
    }
}
