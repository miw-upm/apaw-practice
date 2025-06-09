package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "directors")
public class DirectorEntity {
    @Id
    private String id;
    private String dni;
    private String name;
    private LocalDate birthdate;
    private String style;

    public DirectorEntity() {}

    public DirectorEntity(String dni, String name, LocalDate birthdate, String style) {
        this.dni = dni;
        this.name = name;
        this.birthdate = birthdate;
        this.style = style;
    }

    public static DirectorEntity fromDirector(Director director) {
        DirectorEntity entity = new DirectorEntity();
        BeanUtils.copyProperties(director, entity);
        return entity;
    }

    public Director toDirector() {
        Director director = new Director();
        BeanUtils.copyProperties(this, director);
        return director;
    }

    public String getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getStyle() {
        return style;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}