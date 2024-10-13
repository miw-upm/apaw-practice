package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.math.BigDecimal;

public class Doctor {
    private String dni;
    private String fullname;
    private BigDecimal salary;
    private String specialty;

    public Doctor() {
    }

    public Doctor(String dni, String fullname, BigDecimal salary,specialty) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
        this.specialty=specialty;
    }

    // Getters
    public String getDni() {
        return dni;
    }

    public String getFullname() {
        return fullname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Doctor{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}

