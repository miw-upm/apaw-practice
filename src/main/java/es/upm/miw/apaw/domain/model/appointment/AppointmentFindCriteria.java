package es.upm.miw.apaw.domain.model.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFindCriteria {

    private AppointmentStatus status;

    private Boolean upcoming;

    private String city;

    private String clientMobile;

    public boolean hasStatus() {
        return this.status != null;
    }

    public boolean hasUpcoming() {
        return this.upcoming != null;
    }

    public boolean hasCity() {
        return this.city != null && !this.city.isBlank();
    }

    public boolean hasClientMobile() {
        return this.clientMobile != null && !this.clientMobile.isBlank();
    }
}
