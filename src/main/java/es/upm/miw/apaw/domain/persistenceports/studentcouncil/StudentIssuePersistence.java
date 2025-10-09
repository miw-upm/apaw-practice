package es.upm.miw.apaw.domain.persistenceports.studentcouncil;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;

public interface StudentIssuePersistence {
    StudentIssue create(StudentIssue studentIssue);
}
