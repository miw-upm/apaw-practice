package es.upm.miw.apaw.domain.model.probate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstateFindCriteria {
    private String fileNumber;
    private Boolean opened;
    private HeirStatus heirStatus;
    private String userMobile;

    public boolean appliesFileNumber() {
        return this.fileNumber != null && !this.fileNumber.isBlank();
    }

    public boolean appliesOpened() {
        return this.opened != null;
    }

    public boolean appliesHeirStatus() {
        return this.heirStatus != null;
    }

    public boolean appliesUserMobile() {
        return this.userMobile != null && !this.userMobile.isBlank();
    }

    public boolean isUnfiltered() {
        return !this.appliesFileNumber() && !this.appliesOpened()
                && !this.appliesHeirStatus() && !this.appliesUserMobile();
    }
}
