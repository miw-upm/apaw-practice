package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.ports.out.meeting.MeetingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MeetingAdapter implements MeetingGateway {
    private final MeetingRepository meetingRepository;
    private final LegalIssueRepository legalIssueRepository;

    @Override
    @Transactional
    public Meeting create(Meeting meeting) {
        MeetingEntity meetingEntity = new MeetingEntity(meeting);
        List<LegalIssueEntity> legalIssueEntities = meeting.getLegalIssues().stream()
                .map(legalIssue -> this.legalIssueRepository.getReferenceById(legalIssue.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        meetingEntity.setLegalIssues(legalIssueEntities);
        this.meetingRepository.save(meetingEntity);
        return meeting;
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.meetingRepository.existsByTitle(title);
    }
}
