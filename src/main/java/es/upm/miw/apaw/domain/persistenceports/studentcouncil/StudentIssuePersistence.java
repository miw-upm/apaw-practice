package es.upm.miw.apaw.domain.persistenceports.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;

import java.util.UUID;

public interface StudentIssuePersistence {
    StudentIssue create(StudentIssue studentIssue);

    StudentIssue update(UUID id, StudentIssue studentIssue);
}
