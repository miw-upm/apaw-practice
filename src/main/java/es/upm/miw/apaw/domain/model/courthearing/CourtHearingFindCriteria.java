package es.upm.miw.apaw.domain.model.courthearing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtHearingFindCriteria {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private Boolean scheduled;

    private String courtCity;

    private String userMobile;

    public boolean isAll() {
        return !this.hasDate() && !this.hasScheduled()
                && !this.hasCourtCity() && !this.hasUserMobile();
    }

    public boolean hasDate() {
        return this.date != null;
    }

    public boolean hasScheduled() {
        return this.scheduled != null;
    }

    public boolean hasCourtCity() {
        return this.courtCity != null && !this.courtCity.isBlank();
    }

    public boolean hasUserMobile() {
        return this.userMobile != null && !this.userMobile.isBlank();
    }
}