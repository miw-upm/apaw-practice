package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw.domain.models.clinic.Diagnosis;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class DiagnosisEntity {

    // Identificador técnico de MongoDB
    @Id
    private String id;

    // Clave de negocio (copiada del Modelo Diagnosis)
    private String code;

    private String diagnosisName;
    private LocalDateTime diagnosisDate;

    // Clave foránea al Animal
    private Long animalMicrochipNumber;

    // Constructor para mapear el Modelo de Dominio (Diagnosis) a la Entidad (DiagnosisEntity)
    public DiagnosisEntity(Diagnosis diagnosis) {
        this.code = diagnosis.getCode();
        this.diagnosisName = diagnosis.getDiagnosisName();
        this.diagnosisDate = diagnosis.getDiagnosisDate();
        this.animalMicrochipNumber = diagnosis.getAnimalMicrochipNumber();
    }

    // Método para mapear la Entidad (DiagnosisEntity) de vuelta al Modelo de Dominio (Diagnosis)
    public Diagnosis toDiagnosis() {
        return Diagnosis.builder()
                .code(this.code)
                .diagnosisName(this.diagnosisName)
                .diagnosisDate(this.diagnosisDate)
                .animalMicrochipNumber(this.animalMicrochipNumber)
                .build();
    }
}