package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.math.BigDecimal;

public class Doctor {
    private String dni;
    private String fullname;
    private String specialization;
    private BigDecimal salary;

    // Constructor, getters, setters
    public Doctor(String dni, String fullname, String specialization, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.specialization = specialization;
        this.salary = salary;
    }

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Doctor{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", specialization='" + specialization + '\'' +
                ", salary=" + salary +
                '}';
    }
}
