package es.upm.miw.apaw.domain.model.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationAppointment {

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime scheduledDate;

    private Integer durationMinutes;

    private String notes;

    private Boolean virtual;

    private UUID locationId;

    @NotNull
    private UUID userId;
}
