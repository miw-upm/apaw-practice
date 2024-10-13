package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.math.BigDecimal;

public class Doctor {
    private String id;
    private String name;
    private BigDecimal salary;
    private String specialty;

    // Constructor
    public Doctor(String id, String name, BigDecimal salary, String specialty) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.specialty = specialty;
    }

    // Default constructor
    public Doctor() {
        // Default constructor
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary; // Getter for salary
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

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", specialty='" + specialty + '\'' +
                '}';
    }
}
