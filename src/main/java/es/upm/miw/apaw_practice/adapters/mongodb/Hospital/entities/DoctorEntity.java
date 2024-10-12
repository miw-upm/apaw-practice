package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.math.BigDecimal;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;

public class DoctorEntity {
    @Id
    private String id;
    private String name;
    private String salary;
    private String hospitalId;

    public DoctorEntity() {
        // Empty constructor for framework
    }

    public DoctorEntity(String id, String name, String salary, String hospitalId) {
        this.id = id;
        this.name = name;
        this.salary =  salary.toString();
        this.hospitalId = hospitalId;
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

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Doctor toDoctor() {
        return new Doctor(this.dni, this.fullname, this.salary);
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                '}';
    }
}
