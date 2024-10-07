package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class PatientEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String dni;

    private String name;
    private Integer age;
    private String medicalHistory;

    @DBRef
    private List<AppointmentEntity> appointmentEntities;

    public PatientEntity(String dni, String name, Integer age, String medicalHistory, List<AppointmentEntity> appointmentEntities) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<AppointmentEntity> getAppointmentEntities() {
        return appointmentEntities;
    }

    public void setAppointmentEntities(List<AppointmentEntity> appointmentEntities) {
        this.appointmentEntities = appointmentEntities;
    }

    public Patient toPatient() {
        List<Appointment> appointments = this.appointmentEntities.stream()
                .map(AppointmentEntity::toAppointment)
                .collect(Collectors.toList());
        return new Patient(dni, name, age, medicalHistory, appointments);
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", appointmentEntities=" + appointmentEntities +
                '}';
    }
}
