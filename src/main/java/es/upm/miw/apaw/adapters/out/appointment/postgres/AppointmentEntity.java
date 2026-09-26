package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.appointment.Appointment;
import es.upm.miw.apaw.domain.model.appointment.AppointmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class AppointmentEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime scheduledDate;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    private String notes;

    @Column(nullable = false)
    private Boolean virtual;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppointmentLocationEntity location;

    @Column(nullable = false)
    private UUID clientId;

    public AppointmentEntity(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this, "location", "client");
        this.clientId = appointment.getClient().getId();
    }

    public Appointment toDomain() {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(this, appointment, "location", "clientId");
        appointment.setClient(UserSnapshot.builder().id(this.clientId).build());
        if (this.location != null) {
            appointment.setLocation(this.location.toDomain());
        }
        return appointment;
    }
}
