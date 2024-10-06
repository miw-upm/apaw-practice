package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appoinment {
    private String Id;
    private LocalDate date;
    private LocalTime time;
    private String location;

    public Appoinment(String id, LocalDate date, LocalTime time, String location) {
        this.Id = id;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Appoinment{" +
                "Id='" + Id + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
}
