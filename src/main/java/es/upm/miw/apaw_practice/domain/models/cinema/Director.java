package es.upm.miw.apaw_practice.domain.models.cinema;

import java.time.LocalDate;

public class Director {
    private String dni;
    private LocalDate birthdate;
    private String style;

    public Director() {
        // Default constructor
    }

    public Director(String dni, LocalDate birthdate, String style) {
        this.dni = dni;
        this.birthdate = birthdate;
        this.style = style;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}