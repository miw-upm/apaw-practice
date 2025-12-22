package es.upm.miw.apaw.adapters.mongodb.clinic.entities;


import es.upm.miw.apaw.domain.models.clinic.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document
public class AppointmentEntity {

    @Id
    private UUID id;
    private LocalDateTime appointmentDate;
    private String reason;
    private List<DiagnosisEntity> diagnoses;

    public AppointmentEntity(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this, "diagnoses");
        this.diagnoses = appointment.getDiagnoses() != null
                ? appointment.getDiagnoses().stream()
                .map(DiagnosisEntity::new)
                .toList()
                : null;
    }

    public Appointment toAppointment() {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(this, appointment, "diagnoses");
        appointment.setDiagnoses(this.diagnoses != null
                ? this.diagnoses.stream()
                .map(DiagnosisEntity::toDiagnosis)
                .toList()
                : null);
        return appointment;
    }
}