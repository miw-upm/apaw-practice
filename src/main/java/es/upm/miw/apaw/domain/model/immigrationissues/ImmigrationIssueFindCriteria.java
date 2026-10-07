package es.upm.miw.apaw.domain.model.immigrationissues;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImmigrationIssueFindCriteria {

    private String clientNationality;

    private Boolean overdue;

    private String lawName;

    private String familyName;

    public boolean isAll() {
        return !this.hasClientNationality() && !this.hasOverdue()
                && !this.hasLawName() && !this.hasFamilyName();
    }

    public boolean hasClientNationality() {
        return this.clientNationality != null && !this.clientNationality.isBlank();
    }

    public boolean hasOverdue() {
        return this.overdue != null;
    }

    public boolean hasLawName() {
        return this.lawName != null && !this.lawName.isBlank();
    }

    public boolean hasFamilyName() {
        return this.familyName != null && !this.familyName.isBlank();
    }
}