package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.model.appointment.AppointmentLocation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppointmentLocationEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String address;

    @Column(nullable = false)
    private String city;

    private String postalCode;

    private String room;

    private Integer floor;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    public AppointmentLocationEntity(AppointmentLocation location) {
        BeanUtils.copyProperties(location, this);
    }

    public AppointmentLocation toDomain() {
        AppointmentLocation location = new AppointmentLocation();
        BeanUtils.copyProperties(this, location);
        return location;
    }
}
