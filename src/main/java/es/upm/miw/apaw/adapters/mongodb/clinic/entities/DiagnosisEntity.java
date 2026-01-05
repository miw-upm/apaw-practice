package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import es.upm.miw.apaw.domain.models.clinic.Diagnosis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DiagnosisEntity {

    private String code;
    private LocalDateTime diagnosisDate;
    private Integer severityLevel;
    private String notes;
    private List<TreatmentEntity> treatments;

    public DiagnosisEntity(Diagnosis diagnosis) {
        BeanUtils.copyProperties(diagnosis, this, "treatments");
        if (diagnosis.getTreatments() != null) {
            this.treatments = diagnosis.getTreatments().stream()
                    .map(TreatmentEntity::new)
                    .toList();
        }
    }

    public Diagnosis toDiagnosis() {
        Diagnosis diagnosis = new Diagnosis();
        BeanUtils.copyProperties(this, diagnosis, "treatments");
        if(this.treatments != null){
            diagnosis.setTreatments(this.treatments.stream()
                    .map(TreatmentEntity::toTreatment)
                    .toList());
        }
        return diagnosis;
    }
}
