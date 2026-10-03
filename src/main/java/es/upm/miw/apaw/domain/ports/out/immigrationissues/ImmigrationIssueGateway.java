package es.upm.miw.apaw.domain.ports.out.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;

public interface ImmigrationIssueGateway {

    ImmigrationIssue create(ImmigrationIssue immigrationIssue);

    boolean existsBySubject(String subject);
}