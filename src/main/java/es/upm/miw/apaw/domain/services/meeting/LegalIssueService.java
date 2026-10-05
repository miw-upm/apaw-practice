package es.upm.miw.apaw.domain.services.meeting;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.LegalIssueResolvedUpdate;
import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;
import es.upm.miw.apaw.domain.ports.out.meeting.LegalIssueGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LegalIssueService {
    private final LegalIssueGateway legalIssueGateway;
    private final UserFinder userFinder;

    public LegalIssue create(LegalIssue legalIssue) {
        if (this.legalIssueGateway.existsByTitle(legalIssue.getTitle())) {
            throw new ConflictException("Legal issue title already exists: " + legalIssue.getTitle());
        }
        legalIssue.doDefault();
        return this.legalIssueGateway.create(legalIssue);
    }

    public List<LegalIssue> findAll() {
        return this.legalIssueGateway.findAll();
    }

    public List<MeetingParticipantReport> findParticipantReport() {
        List<MeetingParticipantReport> reports = this.legalIssueGateway.findParticipantReport();
        if (reports.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = reports.stream()
                .map(report -> report.getUserSnapshot().getId())
                .collect(Collectors.toSet());
        return this.toSummaries(reports, this.userFinder.findByIds(userIds));
    }

    private List<MeetingParticipantReport> toSummaries(
            List<MeetingParticipantReport> reports, List<UserSnapshot> users) {
        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return reports.stream()
                .map(report -> this.enrichUserSnapshot(report, usersById))
                .toList();
    }

    private MeetingParticipantReport enrichUserSnapshot(
            MeetingParticipantReport report, Map<UUID, UserSnapshot> usersById) {
        UUID userId = report.getUserSnapshot().getId();
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        report.setUserSnapshot(user);
        return report;
    }

    public LegalIssue read(UUID id) {
        return this.legalIssueGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Legal issue id not found: " + id));
    }

    public LegalIssue update(UUID id, LegalIssue legalIssue) {
        LegalIssue storedLegalIssue = this.read(id);
        if (!storedLegalIssue.getTitle().equals(legalIssue.getTitle())
                && this.legalIssueGateway.existsByTitle(legalIssue.getTitle())) {
            throw new ConflictException("Legal issue title already exists: " + legalIssue.getTitle());
        }
        storedLegalIssue.setTitle(legalIssue.getTitle());
        storedLegalIssue.setDescription(legalIssue.getDescription());
        storedLegalIssue.setPriority(legalIssue.getPriority());
        storedLegalIssue.setResolved(Boolean.TRUE.equals(legalIssue.getResolved()));
        return this.legalIssueGateway.update(storedLegalIssue);
    }

    @Transactional
    public void updateResolvedStates(List<LegalIssueResolvedUpdate> updates) {
        this.assertUniqueIds(updates);
        List<LegalIssue> legalIssues = updates.stream()
                .map(update -> {
                    LegalIssue legalIssue = this.read(update.id());
                    legalIssue.setResolved(update.resolved());
                    return legalIssue;
                })
                .toList();
        legalIssues.forEach(this.legalIssueGateway::update);
    }

    private void assertUniqueIds(List<LegalIssueResolvedUpdate> updates) {
        Set<UUID> ids = new HashSet<>();
        for (LegalIssueResolvedUpdate update : updates) {
            if (!ids.add(update.id())) {
                throw new BadRequestException("Repeated legal issue id: " + update.id());
            }
        }
    }

    public void delete(UUID id) {
        if (this.legalIssueGateway.isReferenced(id)) {
            throw new ConflictException("Legal issue is referenced by a meeting: " + id);
        }
        this.legalIssueGateway.delete(id);
    }
}
