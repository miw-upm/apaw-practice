package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String location;

    public Appointment() {
    }

    public Appointment(Integer id, LocalDate date, LocalTime time, String location) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
}
