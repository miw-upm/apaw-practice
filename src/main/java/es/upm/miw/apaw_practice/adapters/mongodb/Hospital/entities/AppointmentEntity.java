package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Document
public class AppointmentEntity {

    @Id
    private String id;
    private LocalDate date;
    private LocalTime time;

    public AppointmentEntity() {
        // Empty constructor for framework
    }

    // Constructor ajustado para recibir parámetros
    public AppointmentEntity(String id, LocalDate date, LocalTime time) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.date = date;
        this.time = time;

    
    }

    public AppointmentEntity(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
        this.id = UUID.randomUUID().toString(); // Ensure a new ID is generated
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




    public Appointment toAppointment() {
        return new Appointment(this.id, this.date, this.time);
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}

