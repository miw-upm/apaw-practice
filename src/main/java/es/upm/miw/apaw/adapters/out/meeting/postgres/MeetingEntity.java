package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MeetingEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private LocalDateTime meetingDate;

    private String location;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private Boolean online;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeetingStatus meetingStatus;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private List<LegalIssueEntity> legalIssues;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "meeting_participant", joinColumns = @JoinColumn(name = "meeting_id"))
    @Column(name = "participant_id", nullable = false)
    private List<UUID> participantIds;

    public MeetingEntity(Meeting meeting) {
        BeanUtils.copyProperties(meeting, this, "legalIssues", "participants");
        this.legalIssues = meeting.getLegalIssues().stream()
                .map(LegalIssueEntity::new)
                .toList();
        this.participantIds = meeting.getParticipants().stream()
                .map(UserSnapshot::getId)
                .toList();
    }

    public Meeting toDomain() {
        Meeting meeting = new Meeting();
        BeanUtils.copyProperties(this, meeting, "legalIssues", "participantIds");
        meeting.setLegalIssues(new ArrayList<>(this.legalIssues.stream()
                .map(LegalIssueEntity::toDomain)
                .toList()));
        meeting.setParticipants(this.participantIds.stream()
                .map(participantId -> UserSnapshot.builder().id(participantId).build())
                .toList());
        return meeting;
    }
}
