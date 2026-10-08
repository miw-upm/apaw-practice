package es.upm.miw.apaw.domain.model.evidencemanagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceFindCriteria {
    public static final int LONG_CUSTODY_MINUTES = 120;

    private Boolean confidential;

    private Boolean longCustody;

    private String action;

    private String custodianFirstName;

    public boolean isAll() {
        return !this.hasConfidential() && !this.hasLongCustody()
                && !this.hasAction() && !this.hasCustodianFirstName();
    }

    public boolean hasConfidential() {
        return this.confidential != null;
    }

    public boolean hasLongCustody() {
        return this.longCustody != null;
    }

    public boolean hasAction() {
        return this.action != null && !this.action.isBlank();
    }

    public boolean hasCustodianFirstName() {
        return this.custodianFirstName != null && !this.custodianFirstName.isBlank();
    }
}