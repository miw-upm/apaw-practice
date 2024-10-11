package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.math.BigDecimal;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;

public class DoctorEntity {
    private String dni;
    private String fullname;
    private BigDecimal salary;

    public DoctorEntity() {
        // Empty constructor for framework
    }

    public DoctorEntity(String dni, String fullname, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
    }

    // Getters and Setters...

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
