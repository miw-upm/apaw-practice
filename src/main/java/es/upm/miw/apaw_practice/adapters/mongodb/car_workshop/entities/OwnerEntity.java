package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities;

import java.time.LocalDate;
import java.util.Objects;

public class OwnerEntity {
    private String dni;
    private String name;
    private LocalDate registrationDate;

    public OwnerEntity() {
        //empty for framework
    }

    public OwnerEntity(String dni, String name) {
        this.dni = dni;
        this.name = name;
        this.registrationDate = LocalDate.now();
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.dni);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.dni, ((OwnerEntity) o).dni);
    }

    @Override
    public String toString() {
        return "OwnerEntity{" +
                "dni='" + this.dni + '\'' +
                ", name='" + this.name + '\'' +
                ", registrationDate=" + this.registrationDate +
                '}';
    }
}
