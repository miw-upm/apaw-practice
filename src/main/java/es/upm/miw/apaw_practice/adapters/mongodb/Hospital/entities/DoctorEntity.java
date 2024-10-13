package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;

public class DoctorEntity {
    private String id;
    private String name;
    private BigDecimal salary;
    private String specialty;

    // Constructor to convert from Doctor to DoctorEntity
    public DoctorEntity(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.salary = doctor.getSalary();
        this.specialty = doctor.getSpecialty();
    }

    // Default constructor
    public DoctorEntity() {}

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getSalary() { return salary; }
    public String getSpecialty() { return specialty; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    // Convert back to Doctor
    public Doctor toDoctor() {
        return new Doctor(this.id, this.name, this.salary, this.specialty);
    }
}
