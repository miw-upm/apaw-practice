package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class VeterinarianEntity {

    @Id
    private String id;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private UUID userId;
    @EqualsAndHashCode.Include
    @Indexed(unique = true)
    private Long licenseNumber;
    private Boolean active;
    private LocalDateTime createdAt;
    private List<UUID> appointments;

    public VeterinarianEntity(Veterinarian veterinarian) {
        BeanUtils.copyProperties(veterinarian, this,"userDto", "appointments");
        this.userId = veterinarian.getUser().getId();
        if(veterinarian.getAppointments() != null) {
            this.appointments = veterinarian.getAppointments().stream()
                    .map(Appointment::getId)
                    .toList();
        }

    }

    public Veterinarian toVeterinarian() {
        Veterinarian veterinarian = new Veterinarian();
        BeanUtils.copyProperties(this, veterinarian, "userId","appointments");
        veterinarian.setUser(new UserDto());
        veterinarian.getUser().setId(this.userId);
        if(this.getAppointments() != null) {
            veterinarian.setAppointments(this.getAppointments()
                    .stream().map(appointmentId -> Appointment.builder().id(appointmentId).build()).toList());
        }
        return veterinarian;
    }
}