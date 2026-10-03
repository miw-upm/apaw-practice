package es.upm.miw.apaw.domain.model.leases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseFindCriteria {

    private LeaseType leaseType;

    private Boolean inForce;

    private AmendmentType amendmentType;

    private String userMobile;

    public boolean hasLeaseType() {
        return this.leaseType != null;
    }

    public boolean hasInForce() {
        return this.inForce != null;
    }

    public boolean hasAmendmentType() {
        return this.amendmentType != null;
    }

    public boolean hasUserMobile() {
        return this.userMobile != null && !this.userMobile.isBlank();
    }
}
