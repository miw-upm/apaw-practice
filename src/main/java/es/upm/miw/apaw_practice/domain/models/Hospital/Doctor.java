package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.math.BigDecimal;

public class Doctor {
    private String id;
    private String name;
    private BigDecimal salary;
    private String specialty; ]

    // Constructor
    public Doctor(String id, String name, BigDecimal salary, String specialty) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.specialty = specialty;
    }


    // Constructor
    public Doctor() {
        // Default constructor
    }


    public Doctor(String id, String name, BigDecimal salary, String hospitalId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hospitalId = hospitalId;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary; // Getter para salary
    }

    public String getHospitalId() {
        return hospitalId; // Getter para hospitalId
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
