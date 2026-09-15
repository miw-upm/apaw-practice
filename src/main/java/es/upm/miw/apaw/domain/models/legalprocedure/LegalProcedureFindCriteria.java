package es.upm.miw.apaw.domain.models.legalprocedure;

import lombok.*;

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
        return this.vatIncluded == null && this.opened == null
                && this.taskStatus == null && this.userMobile == null;
    }
}
