package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class PatientEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String dni;

    private String fullname;
    private LocalDate dateOfBirth;
    private Boolean hasInsurance;

    @DBRef
    private List<AppointmentEntity> appointmentEntities;

    public PatientEntity(String dni, String fullname, LocalDate dateOfBirth, Boolean hasInsurance, List<AppointmentEntity> appointmentEntities) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.hasInsurance = hasInsurance;
        this.appointmentEntities = appointmentEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public List<AppointmentEntity> getAppointmentEntities() {
        return appointmentEntities;
    }

    public void setAppointmentEntities(List<AppointmentEntity> appointmentEntities) {
        this.appointmentEntities = appointmentEntities;
    }

    public Patient toPatient() {
        return new Patient(
                this.dni,
                this.fullname,
                this.dateOfBirth,
                this.hasInsurance,
                this.appointmentEntities.stream()
                        .map(AppointmentEntity::toAppointment)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public int hashCode() {
        return this.dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && this.dni.equals(((PatientEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hasInsurance=" + hasInsurance +
                ", appointmentEntities=" + appointmentEntities +
                '}';
    }
}
