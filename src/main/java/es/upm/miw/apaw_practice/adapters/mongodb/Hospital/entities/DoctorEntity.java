package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DoctorEntity {

    @Id
    private String id;
    private String name;
    private String salary;
    private String hospitalId;


    public DoctorEntity(String name, BigDecimal salary, String hospitalId) {
        this.name = name;
        this.salary = salary.toString();
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

    public void setSalary(BigDecimal salary) {
        this.salary = salary.toString();
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "DoctorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                '}';
    }
}
