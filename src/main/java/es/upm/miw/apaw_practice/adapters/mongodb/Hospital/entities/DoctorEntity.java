package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class DoctorEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String dni;

    private String name;
    private String specialty;
    private Double salary;

    @DBRef
    private List<AppointmentEntity> appointmentEntities;

    public DoctorEntity(String dni, String name, String specialty, Double salary, List<AppointmentEntity> appointmentEntities) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.name = name;
        this.specialty = specialty;
        this.salary = salary;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<AppointmentEntity> getAppointmentEntities() {
        return appointmentEntities;
    }

    public void setAppointmentEntities(List<AppointmentEntity> appointmentEntities) {
        this.appointmentEntities = appointmentEntities;
    }

    public Doctor toDoctor() {
        List<Appointment> appointments = this.appointmentEntities.stream()
                .map(AppointmentEntity::toAppointment)
                .collect(Collectors.toList());
        return new Doctor(dni, name, specialty, salary, appointments);
    }

    @Override
    public int hashCode() {
        return this.dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && dni.equals(((DoctorEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", salary=" + salary +
                ", appointmentEntities=" + appointmentEntities +
                '}';
    }
}
