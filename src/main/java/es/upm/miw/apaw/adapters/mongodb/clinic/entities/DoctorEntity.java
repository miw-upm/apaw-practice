package es.upm.miw.apaw.adapters.mongodb.clinic.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw.domain.models.clinic.Doctor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class DoctorEntity {

    // Identificador técnico de MongoDB
    @Id
    private String id;

    // Clave de negocio (copiada del Modelo Doctor)
    private Long licenseNumber;

    private String name;
    private String specialty;

    // Clave foránea al usuario externo (UserDto)
    private UUID userId;

    // Constructor para mapear el Modelo de Dominio (Doctor) a la Entidad (DoctorEntity)
    public DoctorEntity(Doctor doctor) {
        this.licenseNumber = doctor.getLicenseNumber();
        this.name = doctor.getName();
        this.specialty = doctor.getSpecialty();
        this.userId = doctor.getUserId();
    }

    // Método para mapear la Entidad (DoctorEntity) de vuelta al Modelo de Dominio (Doctor)
    public Doctor toDoctor() {
        return Doctor.builder()
                .licenseNumber(this.licenseNumber)
                .name(this.name)
                .specialty(this.specialty)
                .userId(this.userId)
                .build();
    }
}