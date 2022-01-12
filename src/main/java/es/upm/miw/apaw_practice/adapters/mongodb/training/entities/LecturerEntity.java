package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class LecturerEntity {
    @Id
    private String id;
    private String dni;
    private LocalDate startDate;
    private Integer experience;

    public LecturerEntity(){
        //empty from framework
    }

    public LecturerEntity(String id, String dni, LocalDate startDate, Integer experience) {
        this.id = id;
        this.dni = dni;
        this.startDate = startDate;
        this.experience = experience;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (dni.equals(((es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "LecturerEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", startDate=" + startDate +
                ", experience=" + experience +
                '}';
    }
}