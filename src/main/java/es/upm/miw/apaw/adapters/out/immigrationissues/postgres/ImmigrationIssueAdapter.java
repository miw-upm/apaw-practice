package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.ImmigrationIssueGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImmigrationIssueAdapter implements ImmigrationIssueGateway {

    private final ImmigrationIssueRepository immigrationIssueRepository;
}