package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class DoctorEntity {
    @Id
    private String doctorId;
    private Long licenseNumber;
    private Boolean isActive;
    // Campo para la relación 1..n (UserDto 1 -> n Doctor)
    private String userId;

    // Se asume un constructor para crear desde el dominio o en el Seeder
    public DoctorEntity(Doctor doctor) {
        this.doctorId = doctor.getDoctorId().toString();
        this.licenseNumber = doctor.getLicenseNumber();
        this.isActive = doctor.getIsActive();
        this.userId = doctor.getUserId().toString();
    }
}