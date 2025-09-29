package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class DiagnosisEntity {
    @Id
    private String diagnosisId;
    private String diagnosisName;
    private LocalDateTime diagnosisDate;
    // Campo para la relación 1..n (Animal 1 *-- n Diagnosis)
    private String animalId;

    public DiagnosisEntity(Diagnosis diagnosis) {
        this.diagnosisId = diagnosis.getDiagnosisId().toString();
        this.diagnosisName = diagnosis.getDiagnosisName();
        this.diagnosisDate = diagnosis.getDiagnosisDate();
        this.animalId = diagnosis.getAnimalId().toString();
    }
}