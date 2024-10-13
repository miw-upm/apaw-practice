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

    @Override
    public String toString() {
        return "Doctor{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", salary=" + salary +
                '}';
    }
}
