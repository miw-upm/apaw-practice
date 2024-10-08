package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appoinment package es.upm.miw.apaw_practice.domain.models.Hospital;

        import java.time.LocalDate;
        import java.time.LocalTime;

public class Appoinment {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String location;

    // Constructor por defecto
    public Appoinment() {
    }

    // Constructor que requiere todos los parámetros
    public Appoinment(String id, LocalDate date, LocalTime time, String location) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "Appoinment{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
}

