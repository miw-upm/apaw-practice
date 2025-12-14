package es.upm.miw.apaw.domain.models.clinic;

import es.upm.miw.apaw.domain.models.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Veterinarian {

    @NotNull
    private UserDto user;

    @NotNull
    private Long licenseNumber;

    @NotNull
    private Boolean active;

    @NotNull
    private LocalDateTime createdAt;

    private List<Appointment> appointments;

    private List<PetVeterinarian> petVeterinarians;
}
