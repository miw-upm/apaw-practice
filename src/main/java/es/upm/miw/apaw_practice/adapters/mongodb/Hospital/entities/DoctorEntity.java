package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;

@Document
public class DoctorEntity {

    @Id
    private String id;
    private String name;
    private String salary;
    private String hospitalId;

    // Constructor
    public DoctorEntity(String name, BigDecimal salary, String hospitalId) {
        this.name = name;
        this.salary = salary.toString();
        this.hospitalId = hospitalId;
    }
    public DoctorEntity toDoctorEntity() {
        return new DoctorEntity(this.name, this.salary, this.hospitalId);
    }

    public Doctor toDoctor() {

        return new Doctor(this.name, new BigDecimal(this.salary), this.hospitalId);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary.toString();
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}

