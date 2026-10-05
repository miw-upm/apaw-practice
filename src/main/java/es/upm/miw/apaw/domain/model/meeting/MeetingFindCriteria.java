package es.upm.miw.apaw.domain.model.meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingFindCriteria {

    private Integer minDurationMinutes;

    private Boolean opened;

    private Integer maxLegalIssuePriority;

    private String participantFirstName;

    public boolean isAll() {
        return !this.hasMinDurationMinutes() && !this.hasOpened()
                && !this.hasMaxLegalIssuePriority() && !this.hasParticipantFirstName();
    }

    public boolean hasMinDurationMinutes() {
        return this.minDurationMinutes != null;
    }

    public boolean hasOpened() {
        return this.opened != null;
    }

    public boolean hasMaxLegalIssuePriority() {
        return this.maxLegalIssuePriority != null;
    }

    public boolean hasParticipantFirstName() {
        return this.participantFirstName != null && !this.participantFirstName.isBlank();
    }
}
