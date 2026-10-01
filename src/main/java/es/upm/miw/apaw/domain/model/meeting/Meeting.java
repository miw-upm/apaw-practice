package es.upm.miw.apaw.domain.model.meeting;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Meeting {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime meetingDate;

    private String location;

    @NotNull
    private Integer durationMinutes;

    private Boolean online;

    private String description;

    private List<LegalIssue> legalIssues;

    private MeetingStatus meetingStatus;

    private List<UserSnapshot> participants;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.online == null) {
            this.online = false;
        }
        if (this.meetingStatus == null) {
            this.meetingStatus = MeetingStatus.SCHEDULED;
        }
    }
}
