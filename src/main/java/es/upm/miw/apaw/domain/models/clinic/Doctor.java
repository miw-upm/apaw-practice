package es.upm.miw.apaw.domain.models.clinic;

import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    // Clave de negocio principal (licenseNumber)
    private Long licenseNumber;

    // Atributos de Doctor
    private String name;
    private String specialty;

    // Relación n..1 con UserDto (implementada con su ID)
    private UUID userId;
}