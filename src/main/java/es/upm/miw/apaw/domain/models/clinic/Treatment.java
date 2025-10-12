package es.upm.miw.apaw.domain.models.clinic;

import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    // Clave de principal
    private String treatmentCode;

    // Atributos de Treatment
    private String procedureName;
    private BigDecimal totalCost;

    // Relación n..1 con Diagnosis
    private String diagnosisCode;
}