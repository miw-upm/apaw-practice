package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Treatment {
    private UUID treatmentId;
    private String procedureName;
    private BigDecimal totalCost;

    private UUID diagnosisId;

    public Treatment(String procedureName, BigDecimal totalCost, UUID diagnosisId) {
        this.treatmentId = UUID.randomUUID();
        this.procedureName = procedureName;
        this.totalCost = totalCost;
        this.diagnosisId = diagnosisId;
    }
}
