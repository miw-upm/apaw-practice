package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.time.LocalDate;
import java.util.Objects;

public class Owner {
    private String dni;
    private String name;
    private LocalDate registrationDate;

    public Owner() {
        //empty for framework
    }

    public Owner(String dni, String name, LocalDate registrationDate) {
        this.dni = dni;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public Owner(String dni, String name) {
        this.dni = dni;
        this.name = name;
        this.registrationDate = LocalDate.now();
    }

    public Owner(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.dni);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.dni, ((Owner) o).dni);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "DNI='" + this.dni + '\'' +
                ", name='" + this.name + '\'' +
                ", registrationDate=" + this.registrationDate +
                '}';
    }
}
