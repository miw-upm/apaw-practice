package es.upm.miw.apaw.domain.model.expertdirectoryservices.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertServiceScheduleFindCriteria {

    private BigDecimal minRateAmount;

    private Boolean withSpecialCondition;

    private String specialtyArea;

    private String userEmail;

    public boolean hasMinRateAmount() {
        return this.minRateAmount != null;
    }

    public boolean hasWithSpecialCondition() {
        return this.withSpecialCondition != null;
    }

    public boolean hasSpecialtyArea() {
        return this.specialtyArea != null && !this.specialtyArea.isBlank();
    }

    public boolean hasUserEmail() {
        return this.userEmail != null && !this.userEmail.isBlank();
    }
}
