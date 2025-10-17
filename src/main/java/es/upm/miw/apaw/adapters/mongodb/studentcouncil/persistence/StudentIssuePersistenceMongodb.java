package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.RepresentativeRepository;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.StudentIssueRepository;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentIssueEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.StudentIssuePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class StudentIssuePersistenceMongodb implements StudentIssuePersistence {

    private final StudentIssueRepository repository;
    private final RepresentativeRepository representativeRepository;

    @Autowired
    public StudentIssuePersistenceMongodb(StudentIssueRepository repository, RepresentativeRepository representativeRepository) {
        this.repository = repository;
        this.representativeRepository = representativeRepository;
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

    @Override
    public StudentIssue update(UUID id, StudentIssue studentIssue) {
        StudentIssueEntity entity = this.repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("StudentIssue id: " + id));


        entity.setStatement(studentIssue.getStatement());
        entity.setClosed(studentIssue.getClosed());
        entity.setUrgency(studentIssue.getUrgency());

        StudentIssueEntity updated = this.repository.save(entity);
        return updated.toStudentIssue();
    }

}
