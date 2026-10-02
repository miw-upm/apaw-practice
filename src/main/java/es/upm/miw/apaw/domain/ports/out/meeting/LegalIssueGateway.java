package es.upm.miw.apaw.domain.ports.out.meeting;

import es.upm.miw.apaw.domain.model.meeting.LegalIssue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LegalIssueGateway {
    LegalIssue create(LegalIssue legalIssue);

    List<LegalIssue> findAll();

    Optional<LegalIssue> read(UUID id);

    LegalIssue update(LegalIssue legalIssue);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    boolean existsByTitle(String title);
}
