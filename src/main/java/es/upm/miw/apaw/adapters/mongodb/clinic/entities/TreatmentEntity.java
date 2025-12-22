package es.upm.miw.apaw.adapters.mongodb.clinic.entities;


import es.upm.miw.apaw.domain.models.clinic.Treatment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TreatmentEntity {

    private String treatmentCode;
    private String description;
    private List<String> medications;
    private BigDecimal totalCost;

    public TreatmentEntity(Treatment treatment) {
        BeanUtils.copyProperties(treatment, this);
    }

    public Treatment toTreatment() {
        Treatment treatment = new Treatment();
        BeanUtils.copyProperties(this, treatment);
        return treatment;
    }
}
