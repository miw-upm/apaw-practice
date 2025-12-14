package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PetVeterinarian {

    @NotNull
    private Pet pet;

    @NotNull
    private Veterinarian veterinarian;

    private LocalDate startDate;
}
