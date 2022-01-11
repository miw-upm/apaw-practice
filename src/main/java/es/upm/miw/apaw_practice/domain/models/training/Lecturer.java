package es.upm.miw.apaw_practice.domain.models.training;

import java.time.LocalDate;

public class Lecturer {
    private String dni;
    private LocalDate startDate;
    private Integer experience;

    public Lecturer() {
        //empty from framework
    }

    public Lecturer(String dni, LocalDate startDate, Integer experience) {
        this.dni = dni;
        this.startDate = startDate;
        this.experience = experience;
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
    public String toString() {
        return "Lecturer{" +
                "dni='" + dni + '\'' +
                ", startDate=" + startDate +
                ", experience=" + experience +
                '}';
    }
}
