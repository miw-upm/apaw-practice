package es.upm.miw.apaw.domain.model.powerofattorney;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerOfAttorneyFindCriteria {

    private PowerOfAttorneyStatus status;
    private Boolean fullMentalCapacity;
    private Boolean legalPowerOfAttorney;
    private String identity;

    public boolean hasStatus() {
        return this.status != null;
    }

    public boolean hasFullMentalCapacity() {
        return this.fullMentalCapacity != null;
    }

    public boolean hasLegalPowerOfAttorney() {
        return this.legalPowerOfAttorney != null;
    }

    public boolean hasIdentity() {
        return this.identity != null && !this.identity.isBlank();
    }
}
