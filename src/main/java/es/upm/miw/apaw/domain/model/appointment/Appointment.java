package es.upm.miw.apaw.domain.model.appointment;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Appointment {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime scheduledDate;

    private Integer durationMinutes;

    private LocalDateTime creationDate;

    private String notes;

    private Boolean virtual;

    private AppointmentStatus status;

    private AppointmentLocation location;

    @NotNull
    private UserSnapshot client;

    public Appointment ofSummary() {
        return Appointment.builder()
                .id(this.id)
                .title(this.title)
                .scheduledDate(this.scheduledDate)
                .status(this.status)
                .virtual(this.virtual)
                .client(UserSnapshot.builder()
                        .id(this.client.getId())
                        .mobile(this.client.getMobile())
                        .firstName(this.client.getFirstName())
                        .build())
                .build();
    }

    public void doDefault() {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
        if (this.durationMinutes == null) {
            this.durationMinutes = 30;
        }
        if (this.virtual == null) {
            this.virtual = false;
        }
        if (this.status == null) {
            this.status = AppointmentStatus.SCHEDULED;
        }
    }
}
