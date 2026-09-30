package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.ports.out.meeting.LegalIssueGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LegalIssueAdapter implements LegalIssueGateway {
    private final LegalIssueRepository legalIssueRepository;
}
