package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Appointment {

    @NotNull
    private UUID id;

    @NotNull
    private LocalDateTime appointmentDate;

    @NotBlank
    private String reason;

    private List<Diagnosis> diagnoses;
}
