package es.upm.miw.apaw.domain.services.meeting;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.CreationMeeting;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingFindCriteria;
import es.upm.miw.apaw.domain.ports.out.meeting.LegalIssueGateway;
import es.upm.miw.apaw.domain.ports.out.meeting.MeetingGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingGateway meetingGateway;
    private final LegalIssueGateway legalIssueGateway;
    private final UserFinder userFinder;

    public Meeting create(CreationMeeting creation) {
        if (this.meetingGateway.existsByTitle(creation.getTitle())) {
            throw new ConflictException("Meeting title already exists: " + creation.getTitle());
        }
        Meeting meeting = new Meeting();
        BeanUtils.copyProperties(creation, meeting);
        meeting.setLegalIssues(creation.getLegalIssueIds().stream()
                .distinct()
                .map(this::readLegalIssue)
                .toList());
        meeting.setParticipants(this.readParticipants(creation.getParticipantIds()));
        meeting.doDefault();
        return this.meetingGateway.create(meeting);
    }

    public List<Meeting> find(MeetingFindCriteria criteria) {
        List<Meeting> meetings = this.meetingGateway.find(criteria);
        if (meetings.isEmpty()) {
            return List.of();
        }
        Set<UUID> userIds = meetings.stream()
                .flatMap(meeting -> meeting.getParticipants().stream())
                .map(UserSnapshot::getId)
                .collect(Collectors.toSet());
        return this.toSummaries(criteria, meetings,
                userIds.isEmpty() ? List.of() : this.userFinder.findByIds(userIds));
    }

    private List<Meeting> toSummaries(
            MeetingFindCriteria criteria, List<Meeting> meetings, List<UserSnapshot> users) {
        Map<UUID, UserSnapshot> usersById = users.stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return meetings.stream()
                .map(meeting -> this.enrichParticipants(meeting, usersById))
                .filter(meeting -> this.hasParticipantWithFirstName(criteria, meeting))
                .toList();
    }

    private Meeting enrichParticipants(Meeting meeting, Map<UUID, UserSnapshot> usersById) {
        meeting.setParticipants(meeting.getParticipants().stream()
                .map(participant -> this.enrichUserSnapshot(participant.getId(), usersById))
                .toList());
        return meeting;
    }

    private UserSnapshot enrichUserSnapshot(UUID userId, Map<UUID, UserSnapshot> usersById) {
        UserSnapshot user = usersById.get(userId);
        if (user == null) {
            throw new NotFoundException("User id not found: " + userId);
        }
        return user;
    }

    private boolean hasParticipantWithFirstName(MeetingFindCriteria criteria, Meeting meeting) {
        return !criteria.hasParticipantFirstName()
                || meeting.getParticipants().stream()
                        .anyMatch(participant -> criteria.getParticipantFirstName()
                                .equalsIgnoreCase(participant.getFirstName()));
    }

    private List<UserSnapshot> readParticipants(List<UUID> participantIds) {
        List<UUID> distinctParticipantIds = participantIds.stream().distinct().toList();
        if (distinctParticipantIds.isEmpty()) {
            return List.of();
        }
        Map<UUID, UserSnapshot> usersById = this.userFinder
                .findByIds(new HashSet<>(distinctParticipantIds)).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        return distinctParticipantIds.stream()
                .map(participantId -> Optional.ofNullable(usersById.get(participantId))
                        .orElseThrow(() -> new NotFoundException("User id not found: " + participantId)))
                .toList();
    }

    private LegalIssue readLegalIssue(UUID id) {
        if (this.legalIssueGateway.isReferenced(id)) {
            throw new ConflictException("Legal issue is already assigned to a meeting: " + id);
        }
        return this.legalIssueGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Legal issue id not found: " + id));
    }
}
