package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appoinment;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;

public class PatientEntity {
    @Id
    private String dni;
    private String fullname;
    private LocalDate dateOfBirth;
    private boolean hasInsurance;
    private AppoinmentEntity appoinment;

    public PatientEntity() {
        // empty for framework
    }

    public PatientEntity(String dni, String fullname, LocalDate dateOfBirth, boolean hasInsurance, AppoinmentEntity appoinment) {
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

    public AppoinmentEntity getAppoinment() {
        return appoinment;
    }

    public void setAppoinment(AppoinmentEntity appoinment) {
        this.appoinment = appoinment;
    }

    public void fromPatient(Patient patient) {
        BeanUtils.copyProperties(patient, this);
    }

    public Patient toPatient() {
        Patient patient = new Patient();
        BeanUtils.copyProperties(this, patient, "appoinment");
        patient.setAppoinment(appoinment.toAppoinment());
        return patient;
    }
}
