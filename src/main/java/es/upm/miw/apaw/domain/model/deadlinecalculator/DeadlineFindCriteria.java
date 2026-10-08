package es.upm.miw.apaw.domain.model.deadlinecalculator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeadlineFindCriteria {

    private String region;

    private Boolean overdue;

    private ScopeLevel scopeLevel;

    private String userMobile;

    public boolean isAll() {
        return !this.hasRegion() && !this.hasOverdue()
                && !this.hasScopeLevel() && !this.hasUserMobile();
    }

    public boolean hasRegion() {
        return this.region != null && !this.region.isBlank();
    }

    public boolean hasOverdue() {
        return this.overdue != null;
    }

    public boolean hasScopeLevel() {
        return this.scopeLevel != null;
    }

    public boolean hasUserMobile() {
        return this.userMobile != null && !this.userMobile.isBlank();
    }
}
