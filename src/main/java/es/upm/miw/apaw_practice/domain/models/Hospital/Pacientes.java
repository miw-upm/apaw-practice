package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Pacientes{
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private Boolean hasInsurance;

    public Pacientes() {
    }

    public Pacientes(Long id, String name, LocalDate dateOfBirth, Boolean hasInsurance) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.hasInsurance = hasInsurance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Pacientes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hasInsurance=" + hasInsurance +
                '}';
    }
}