package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingFindCriteria;
import es.upm.miw.apaw.domain.model.meeting.MeetingStatus;
import es.upm.miw.apaw.domain.ports.out.meeting.MeetingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public List<Meeting> find(MeetingFindCriteria criteria) {
        Specification<MeetingEntity> specification = this.buildSpecification(criteria);
        return this.meetingRepository.findAll(specification, Sort.by("meetingDate")).stream()
                .map(this::toDomainWithoutLegalIssues)
                .toList();
    }

    private Meeting toDomainWithoutLegalIssues(MeetingEntity entity) {
        Meeting meeting = new Meeting();
        BeanUtils.copyProperties(entity, meeting, "legalIssues", "participantIds");
        meeting.setParticipants(entity.getParticipantIds().stream()
                .map(participantId -> UserSnapshot.builder().id(participantId).build())
                .toList());
        return meeting;
    }

    private Specification<MeetingEntity> buildSpecification(MeetingFindCriteria criteria) {
        Specification<MeetingEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasMinDurationMinutes()) {
            specification = specification.and((root, query, builder) -> builder
                    .greaterThanOrEqualTo(root.get("durationMinutes"), criteria.getMinDurationMinutes()));
        }
        specification = this.addOpened(specification, criteria.getOpened());
        specification = this.addMaxLegalIssuePriority(specification, criteria.getMaxLegalIssuePriority());
        return specification;
    }

    private Specification<MeetingEntity> addOpened(
            Specification<MeetingEntity> specification, Boolean opened) {
        if (opened == null) {
            return specification;
        }
        return specification.and((root, query, builder) -> opened
                ? builder.and(
                        builder.equal(root.get("meetingStatus"), MeetingStatus.SCHEDULED),
                        builder.greaterThanOrEqualTo(root.get("meetingDate"), LocalDateTime.now()))
                : builder.or(
                        builder.notEqual(root.get("meetingStatus"), MeetingStatus.SCHEDULED),
                        builder.lessThan(root.get("meetingDate"), LocalDateTime.now())));
    }

    private Specification<MeetingEntity> addMaxLegalIssuePriority(
            Specification<MeetingEntity> specification, Integer maxLegalIssuePriority) {
        if (maxLegalIssuePriority == null) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.lessThanOrEqualTo(root.join("legalIssues").get("priority"), maxLegalIssuePriority);
        });
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.meetingRepository.existsByTitle(title);
    }
}
