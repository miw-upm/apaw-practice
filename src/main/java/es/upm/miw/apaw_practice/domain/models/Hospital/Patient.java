package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.time.LocalDate;

public class Patient {
    private String dni;
    private String fullname;
    private LocalDate dateOfBirth;
    private boolean hasInsurance;
    private Appoinment appoinment; // Cambié el nombre a 'appoinment' para que sea coherente.

    public Patient(String dni, String fullname, LocalDate dateOfBirth, boolean hasInsurance, Appoinment appoinment) {
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

    public Appoinment getAppoinment() {
        return appoinment;
    }

    public void setAppoinment(Appoinment appoinment) {
        this.appoinment = appoinment;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hasInsurance=" + hasInsurance +
                ", appoinment=" + appoinment +
                '}';
    }
}
