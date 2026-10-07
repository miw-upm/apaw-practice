package es.upm.miw.apaw.domain.model.secondlawchance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExonerationCaseFindCriteria {

    private String lawyer;

    private Boolean opened;

    private CreditorType creditorType;

    private String userMobile;

    public boolean hasLawyer() {
        return this.lawyer != null && !this.lawyer.isBlank();
    }

    public boolean hasOpened() {
        return this.opened != null;
    }

    public boolean hasCreditorType() {
        return this.creditorType != null;
    }

    public boolean hasUserMobile() {
        return this.userMobile != null && !this.userMobile.isBlank();
    }
}
