package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.time.LocalDate;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import java.util.List;

public class Patient {
    private String dni;
    private String fullname;
    private LocalDate dateOfBirth;
    private boolean hasInsurance;
    private  Appointment Appoinment;

    public Patient(String dni, String fullname, LocalDate dateOfBirth, boolean hasInsurance, Appointment appoinment) {
        this.dni = dni;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.hasInsurance = hasInsurance;
        this.appoinment = appoinment;
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

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public Appointment getAppoinment() {
        return Appoinment;
    }

    public void setAppoinment(Appointment appoinment) {
        Appoinment = appoinment;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Patient{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hasInsurance=" + hasInsurance +
                ", Appoinment=" + Appoinment +
                '}';
    }
}
