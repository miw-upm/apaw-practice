package es.upm.miw.apaw.domain.models.clinic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    // Clave de negocio principal (licenseNumber)
    @NotNull
    private Long licenseNumber;

    // Atributos de Doctor
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String specialty;

    // Relación n..1 con UserDto (implementada con su ID)
    private UUID userId;
}