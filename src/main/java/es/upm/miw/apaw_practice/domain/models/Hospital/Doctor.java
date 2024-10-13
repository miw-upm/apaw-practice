package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.math.BigDecimal;

public class Doctor {
    private String dni;
    private String fullname;
    private BigDecimal salary;

    public Doctor() {
    }

    public Doctor(String dni, String fullname, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Doctor{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                '}';
    }
}

