package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appoinment;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppoinmentEntity {
    @Id
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String location;

    public AppoinmentEntity() {
        // empty for framework
    }

    public AppoinmentEntity(String id, LocalDate date, LocalTime time, String location) {
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

    public void fromAppoinment(Appoinment appoinment) {
        BeanUtils.copyProperties(appoinment, this);
    }

    public Appoinment toAppoinment() {
        return new Appoinment(this.id, this.date, this.time, this.location);
    }
}
