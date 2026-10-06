package es.upm.miw.apaw.domain.model.carreservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFindCriteria {

    private Integer durationMinutes;
    private Boolean active;
    private String carLicensePlate;
    private String userCity;

    public boolean isAll() {
        return !this.hasDurationMinutes() && !this.hasActive()
                && !this.hasCarLicensePlate() && !this.hasUserCity();
    }

    public boolean hasDurationMinutes() {
        return this.durationMinutes != null;
    }

    public boolean hasActive() {
        return this.active != null;
    }

    public boolean hasCarLicensePlate() {
        return this.carLicensePlate != null && !this.carLicensePlate.isBlank();
    }

    public boolean hasUserCity() {
        return this.userCity != null && !this.userCity.isBlank();
    }
}