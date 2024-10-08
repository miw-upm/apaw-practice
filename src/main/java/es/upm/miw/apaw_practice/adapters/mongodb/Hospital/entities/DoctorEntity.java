package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class DoctorEntity {
    @Id
    private String dni;
    private String fullname;
    private BigDecimal salary;

    public DoctorEntity() {
        // empty for framework
    }

    public DoctorEntity(String dni, String fullname, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void fromDoctor(Doctor doctor) {
        BeanUtils.copyProperties(doctor, this);
    }

    public Doctor toDoctor() {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(this, doctor);
        return doctor;
    }
}
