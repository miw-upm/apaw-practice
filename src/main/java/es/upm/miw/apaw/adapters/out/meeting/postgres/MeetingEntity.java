package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.meeting.MeetingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
}
