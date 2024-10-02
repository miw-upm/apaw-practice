package es.upm.miw.apaw_practice.domain.models.shop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Doctores {
    private Long id;
    private String name;
    private String specialization;
    private BigDecimal salary;

    public Doctores() {
    }

    public Doctores(Long id, String name, String specialization, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.salary = salary;
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
        return "Doctores{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", salary=" + salary +
                '}';
    }
}