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

    private Boolean openToPublic;

    private Boolean remote;

    private CourtHearingType type;

    private CourtHearingStatus status;

    @NotEmpty
    private List<UserSnapshot> attendees;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.openToPublic == null) {
            this.openToPublic = false;
        }
        if (this.remote == null) {
            this.remote = false;
        }
        if (this.status == null) {
            this.status = CourtHearingStatus.SCHEDULED;
        }
    }

    public CourtHearing ofSummary() {
        return CourtHearing.builder()
                .id(this.id)
                .date(this.date)
                .roomNumber(this.roomNumber)
                .type(this.type)
                .status(this.status)
                .openToPublic(this.openToPublic)
                .remote(this.remote)
                .attendees(this.attendees == null ? null : this.attendees.stream()
                        .map(attendee -> UserSnapshot.builder()
                                .id(attendee.getId())
                                .mobile(attendee.getMobile())
                                .firstName(attendee.getFirstName())
                                .build())
                        .toList())
                .build();
    }
}