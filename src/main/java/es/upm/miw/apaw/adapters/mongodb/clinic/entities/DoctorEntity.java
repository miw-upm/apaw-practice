package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import es.upm.miw.apaw.domain.models.clinic.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class DoctorEntity {

    @Id
    private String id; // <--- DEBE ESTAR

    @Indexed(unique = true)
    private Long licenseNumber;

    private String name;
    private String specialty;
    private UUID userId;

    // CONSTRUCTOR DE MAPEO (Doctor -> DoctorEntity)
    public DoctorEntity(Doctor doctor) {
        this.id = doctor.getId();
        this.licenseNumber = doctor.getLicenseNumber();
        this.name = doctor.getName();
        this.specialty = doctor.getSpecialty();
        this.userId = doctor.getUserId();
    }

    // MÉTODO DE CONVERSIÓN (DoctorEntity -> Doctor)

    public Doctor toDoctor() {
        return Doctor.builder()
                .id(this.id)
                .licenseNumber(this.licenseNumber)
                .name(this.name)
                .specialty(this.specialty)
                .userId(this.userId)
                .build();
    }
}