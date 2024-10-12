package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
public class DoctorEntity {

    @Id
    private String id;

    private String fullname;
    private BigDecimal salary;
    private String hospitalId;

    public DoctorEntity() {
        // Empty constructor for framework
    }

    public DoctorEntity(Doctor doctor) {
        BeanUtils.copyProperties(doctor, this);
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    // Conversion methods
    public void fromDoctor(Doctor doctor) {
        BeanUtils.copyProperties(doctor, this);
    }

    public Doctor toDoctor() {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(this, doctor);
        return doctor;
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                ", hospitalId='" + hospitalId + '\'' +
                '}';
    }
}

