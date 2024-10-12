package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.time.LocalDate;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;

public class PatientEntity {
    private String dni;
    private String fullname;
    private LocalDate dateOfBirth;
    private boolean hasInsurance;
    private Appointment appointment;

    // Constructor vacío para el marco
    public PatientEntity() {
        // Empty constructor for framework
    }

    // Constructor ajustado para recibir un objeto Patient
    public PatientEntity(Patient patient) {
        this.dni = patient.getDni();
        this.fullname = patient.getFullname();
        this.dateOfBirth = patient.getDateOfBirth();
        this.hasInsurance = patient.isHasInsurance();
        this.appointment = patient.getAppointment(); // Asegúrate de que este método exista en Patient
    }

    // Método para convertir PatientEntity a Patient
    public Patient toPatient() {
        return new Patient(this.dni, this.fullname, this.dateOfBirth, this.hasInsurance, this.appointment);
    }

    // Getters y Setters
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

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hasInsurance=" + hasInsurance +
                ", appointment=" + appointment +
                '}';
    }
}
