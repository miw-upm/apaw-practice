package es.upm.miw.apaw.domain.ports.out.immigrationissues;

import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssueFindCriteria;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;

import java.util.List;

public interface ImmigrationIssueGateway {

    ImmigrationIssue create(ImmigrationIssue immigrationIssue);

    boolean existsBySubject(String subject);

    List<ImmigrationIssue> find(ImmigrationIssueFindCriteria criteria);

    List<LawBasisUsageReport> findLawBasisUsageReport();
}