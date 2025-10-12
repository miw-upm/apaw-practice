package es.upm.miw.apaw.domain.models.clinic;

import lombok.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    // Clave de negocio principal (code)
    private String code;

    // Atributos de Diagnosis
    private String diagnosisName;
    private LocalDateTime diagnosisDate;

    // Relación n..1 con Animal (usando la clave microchipNumber)
    private Long animalMicrochipNumber;
}