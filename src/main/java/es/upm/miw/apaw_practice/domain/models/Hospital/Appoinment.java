package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appoinment {
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String clientName;

    public Appoinment(LocalDate date, LocalTime time, String location, String clientName) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.clientName = clientName;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Appoinment{" +
                "date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
