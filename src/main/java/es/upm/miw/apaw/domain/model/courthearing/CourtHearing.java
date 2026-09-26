package es.upm.miw.apaw.domain.model.courthearing;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class CourtHearing {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private LocalDateTime date;

    @NotBlank
    private String roomNumber;

    private String transcript;

    private Integer durationMinutes;

    private Boolean isPublic;

    private Boolean isRemote;

    private CourtHearingType courtHearingType;

    private CourtHearingStatus courtHearingStatus;

    @NotEmpty
    private List<UserSnapshot> attendees;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.isPublic == null) {
            this.isPublic = false;
        }
        if (this.isRemote == null) {
            this.isRemote = false;
        }
        if (this.courtHearingStatus == null) {
            this.courtHearingStatus = CourtHearingStatus.SCHEDULED;
        }
    }

    public boolean isScheduled() {
        return this.courtHearingStatus == CourtHearingStatus.SCHEDULED;
    }
}