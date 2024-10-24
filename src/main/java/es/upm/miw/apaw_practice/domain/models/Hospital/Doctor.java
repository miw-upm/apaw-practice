package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.math.BigDecimal;

import java.util.List;



public class Doctor {

    private String dni;
    private String fullname;
    private BigDecimal salary;

    // Constructor
    public Doctor(String dni, String fullname, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
    }

    // Getters and setters
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
