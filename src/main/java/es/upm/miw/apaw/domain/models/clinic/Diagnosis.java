package es.upm.miw.apaw.domain.models.clinic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Diagnosis {
    private UUID diagnosisId;
    private String diagnosisName;
    private LocalDateTime diagnosisDate;

    private UUID animalId;

    public Diagnosis(String diagnosisName, UUID animalId) {
        this.diagnosisId = UUID.randomUUID();
        this.diagnosisName = diagnosisName;
        this.diagnosisDate = LocalDateTime.now();
        this.animalId = animalId;
    }
}
