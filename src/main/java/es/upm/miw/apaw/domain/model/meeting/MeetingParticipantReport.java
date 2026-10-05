package es.upm.miw.apaw.domain.model.meeting;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingParticipantReport {
    private UserSnapshot userSnapshot;
    private long meetingCount;
    private long legalIssueCount;
    private double averageLegalIssuePriority;

    public MeetingParticipantReport(
            UUID userId, long meetingCount, long legalIssueCount, double averageLegalIssuePriority) {
        this.userSnapshot = UserSnapshot.builder().id(userId).build();
        this.meetingCount = meetingCount;
        this.legalIssueCount = legalIssueCount;
        this.averageLegalIssuePriority = averageLegalIssuePriority;
    }
}
