package es.upm.miw.apaw.domain.model.judicialcourt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudicialCourtFindCriteria {

    private String city;

    private Boolean completeContactInformation;

    private String jurisdiction;

    private String userIdentity;

    public boolean isAll() {
        return this.city == null || this.city.isBlank()
                && this.completeContactInformation == null
                && (this.jurisdiction == null || this.jurisdiction.isBlank())
                && (this.userIdentity == null || this.userIdentity.isBlank());
    }

    public boolean hasCity() {
        return this.city != null && !this.city.isBlank();
    }

    public boolean hasCompleteContactInformation() {
        return this.completeContactInformation != null;
    }

    public boolean hasJurisdiction() {
        return this.jurisdiction != null && !this.jurisdiction.isBlank();
    }

    public boolean isUserIdentitySet() {
        return this.userIdentity != null && !this.userIdentity.isBlank();
    }
}
