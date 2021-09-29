package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.time.LocalDate;
import java.util.Objects;

public class Owner {
    private String DNI;
    private String name;
    private LocalDate registrationDate;

    public Owner() {
        //empty for framework
    }

    public Owner(String DNI, String name, LocalDate registrationDate) {
        this.DNI = DNI;
        this.name = name;
        this.registrationDate = registrationDate;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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
        return Objects.hashCode(this.DNI);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.DNI, ((Owner) o).DNI);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "DNI='" + this.DNI + '\'' +
                ", name='" + this.name + '\'' +
                ", registrationDate=" + this.registrationDate +
                '}';
    }
}
