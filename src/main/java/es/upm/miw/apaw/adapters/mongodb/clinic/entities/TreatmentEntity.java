package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw.domain.models.clinic.Treatment;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class TreatmentEntity {
    @Id
    private String treatmentId;
    private String procedureName;
    private BigDecimal totalCost;
    // Campo para la relación n..1 (Diagnosis 1 o-- n Treatment)
    private String diagnosisId;

    public TreatmentEntity(Treatment treatment) {
        this.treatmentId = treatment.getTreatmentId().toString();
        this.procedureName = treatment.getProcedureName();
        this.totalCost = treatment.getTotalCost();
        this.diagnosisId = treatment.getDiagnosisId().toString();
    }
}