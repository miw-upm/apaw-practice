package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appoinment {
    private Id String;
    private LocalDate date;
    private LocalTime time;
    private String location;

    public Appoinment(Id string, LocalDate date, LocalTime time, String location) {
        String = string;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public Id getString() {
        return String;
    }

    public void setString(Id string) {
        String = string;
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
                "String=" + String +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
}
