package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw.domain.models.clinic.Treatment;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class TreatmentEntity {

    // Identificador técnico de MongoDB
    @Id
    private String id;

    // Clave de negocio (copiada del Modelo Treatment)
    private String treatmentCode;

    private String procedureName;
    private BigDecimal totalCost;

    // Clave foránea a la Diagnosis
    private String diagnosisCode;

    // Constructor para mapear el Modelo de Dominio (Treatment) a la Entidad (TreatmentEntity)
    public TreatmentEntity(Treatment treatment) {
        this.treatmentCode = treatment.getTreatmentCode();
        this.procedureName = treatment.getProcedureName();
        this.totalCost = treatment.getTotalCost();
        this.diagnosisCode = treatment.getDiagnosisCode();
    }

    // Método para mapear la Entidad (TreatmentEntity) de vuelta al Modelo de Dominio (Treatment)
    public Treatment toTreatment() {
        return Treatment.builder()
                .treatmentCode(this.treatmentCode)
                .procedureName(this.procedureName)
                .totalCost(this.totalCost)
                .diagnosisCode(this.diagnosisCode)
                .build();
    }
}