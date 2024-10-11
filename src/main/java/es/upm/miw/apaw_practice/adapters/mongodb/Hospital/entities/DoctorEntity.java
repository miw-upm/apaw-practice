package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.math.BigDecimal;

public class DoctorEntity {
    private String dni;
    private String fullname;
    private BigDecimal salary;

    // Constructor that accepts parameters
    public DoctorEntity(String dni, String fullname, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
    }

    // Getters and Setters
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
}

