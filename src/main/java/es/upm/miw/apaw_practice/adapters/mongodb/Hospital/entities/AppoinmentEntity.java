package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document
public class AppointmentEntity {

    @Id
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String location;

    // Getters, Setters, Constructor, equals, hashCode, toString...

    public AppointmentEntity() {
    }

    public AppointmentEntity(Integer id, LocalDate date, LocalTime time, String location) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
    }

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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AppointmentEntity)) return false;
        if (!super.equals(object)) return false;
        AppointmentEntity that = (AppointmentEntity) object;
        return java.util.Objects.equals(getId(), that.getId()) && java.util.Objects.equals(getDate(), that.getDate()) && java.util.Objects.equals(getTime(), that.getTime()) && java.util.Objects.equals(getLocation(), that.getLocation());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getDate(), getTime(), getLocation());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "AppointmentEntity{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
}
