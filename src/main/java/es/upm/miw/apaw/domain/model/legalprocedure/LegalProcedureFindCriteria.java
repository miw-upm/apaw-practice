package es.upm.miw.apaw.domain.model.legalprocedure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalProcedureFindCriteria {

    private Boolean vatIncluded;

    private Boolean opened;

    private TaskStatus taskStatus;

    private String userMobile;

    public boolean isAll() {
        return !this.hasVatIncluded() && !this.hasOpened()
                && !this.hasTaskStatus() && !this.hasUserMobile();
    }

    public boolean hasVatIncluded() {
        return this.vatIncluded != null;
    }

    public boolean hasOpened() {
        return this.opened != null;
    }

    public boolean hasTaskStatus() {
        return this.taskStatus != null;
    }

    public boolean hasUserMobile() {
        return this.userMobile != null && !this.userMobile.isBlank();
    }
}
