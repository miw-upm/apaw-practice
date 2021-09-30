package es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities;

import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class CaretakerEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String name;
    private String surname;

    public CaretakerEntity() {
        //empty from framework
    }

    public CaretakerEntity(Caretaker caretaker) {
        BeanUtils.copyProperties(caretaker, this);
        this.id = UUID.randomUUID().toString();
    }

    public Caretaker toCaretaker() {
        return new Caretaker(this.dni, this.name, this.surname);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || obj != null
                && getClass() == obj.getClass()
                && dni.equals(((CaretakerEntity) obj).dni);
    }

    @Override
    public String toString() {
        return "Caretaker{" +
                "DNI='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
