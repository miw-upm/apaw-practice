package es.upm.miw.apaw.domain.model.euregulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceAssessmentFindCriteria {

    // Null values are ignored so any subset of assessment filters can be combined.
    private String responsibleLawyer;

    // Uses the deadline distance also calculated by the overdue compliance report.
    private Integer daysToNearestDeadline;

    private ApplicationArea applicationArea;

    private String userFirstName;

    public boolean isAll() {
        return !this.hasResponsibleLawyer() && !this.hasDaysToNearestDeadline()
                && !this.hasApplicationArea() && !this.hasUserFirstName();
    }

    public boolean hasResponsibleLawyer() {
        return this.responsibleLawyer != null && !this.responsibleLawyer.isBlank();
    }

    public boolean hasDaysToNearestDeadline() {
        return this.daysToNearestDeadline != null;
    }

    public boolean hasApplicationArea() {
        return this.applicationArea != null;
    }

    public boolean hasUserFirstName() {
        return this.userFirstName != null && !this.userFirstName.isBlank();
    }
}
