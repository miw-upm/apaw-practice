package es.upm.miw.apaw.domain.models.clinic;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    // Clave de negocio principal (microchipNumber)
    private Long microchipNumber;

    // Atributos de Animal
    private String petName;
    private Double weightKilos;
    private Boolean vaccinated;

    // Relación n..1 con Doctor (usando la clave licenseNumber)
    private Long doctorLicenseNumber;
}