package es.upm.miw.apaw.domain.ports.out.meeting;

import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LegalIssueGateway {
    LegalIssue create(LegalIssue legalIssue);

    List<LegalIssue> findAll();

    List<MeetingParticipantReport> findParticipantReport();

    Optional<LegalIssue> read(UUID id);

    LegalIssue update(LegalIssue legalIssue);

    void delete(UUID id);

    boolean isReferenced(UUID id);

    boolean existsByTitle(String title);
}
