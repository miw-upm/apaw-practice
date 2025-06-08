package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "directors")
public class DirectorEntity {
    @Id
    private String dni;
    private LocalDate birthdate;
    private String style;

    public DirectorEntity() {}

    public DirectorEntity(String dni, LocalDate birthdate, String style) {
        this.dni = dni;
        this.birthdate = birthdate;
        this.style = style;
    }

    // Getters and setters

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