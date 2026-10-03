package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.ports.out.meeting.LegalIssueGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LegalIssueAdapter implements LegalIssueGateway {
    private final LegalIssueRepository legalIssueRepository;
    private final MeetingRepository meetingRepository;

    @Override
    public LegalIssue create(LegalIssue legalIssue) {
        return this.legalIssueRepository
                .save(new LegalIssueEntity(legalIssue))
                .toDomain();
    }

    @Override
    public List<LegalIssue> findAll() {
        return this.legalIssueRepository.findAllByOrderByTitleAsc().stream()
                .map(LegalIssueEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<LegalIssue> read(UUID id) {
        return this.legalIssueRepository.findById(id)
                .map(LegalIssueEntity::toDomain);
    }

    @Override
    public LegalIssue update(LegalIssue legalIssue) {
        return this.legalIssueRepository
                .save(new LegalIssueEntity(legalIssue))
                .toDomain();
    }

    @Override
    public void delete(UUID id) {
        this.legalIssueRepository.deleteById(id);
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.meetingRepository.existsByLegalIssuesId(id);
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.legalIssueRepository.existsByTitle(title);
    }
}
