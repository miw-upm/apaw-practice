package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.math.BigDecimal;


public class Doctor {

    private String dni;
    private String fullname;
    private Double salary;

    // Constructor
    public Doctor(String dni, String fullname, Double salary) {
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
