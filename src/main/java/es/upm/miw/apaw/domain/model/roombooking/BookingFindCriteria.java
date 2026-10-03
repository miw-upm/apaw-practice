package es.upm.miw.apaw.domain.model.roombooking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingFindCriteria {

    private Integer estimatedAttendees;

    private Boolean ongoing;

    private Boolean videoconferenceEquipped;

    private String userEmail;

    public boolean isAll() {
        return !this.hasEstimatedAttendees() && !this.hasOngoing()
                && !this.hasVideoconferenceEquipped() && !this.hasUserEmail();
    }

    public boolean hasEstimatedAttendees() {
        return this.estimatedAttendees != null;
    }

    public boolean hasOngoing() {
        return this.ongoing != null;
    }

    public boolean hasVideoconferenceEquipped() {
        return this.videoconferenceEquipped != null;
    }

    public boolean hasUserEmail() {
        return this.userEmail != null && !this.userEmail.isBlank();
    }
}