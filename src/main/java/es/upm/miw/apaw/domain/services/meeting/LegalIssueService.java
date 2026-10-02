package es.upm.miw.apaw.domain.services.meeting;

import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.LegalIssueResolvedUpdate;
import es.upm.miw.apaw.domain.ports.out.meeting.LegalIssueGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LegalIssueService {
    private final LegalIssueGateway legalIssueGateway;

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
