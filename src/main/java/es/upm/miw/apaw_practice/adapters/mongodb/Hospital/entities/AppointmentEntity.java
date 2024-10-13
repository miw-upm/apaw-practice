package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class AppointmentEntity {

    private String id;
    private String patientDni;
    private LocalDate date;
    private LocalTime time;
    private String room;


    public AppointmentEntity() {

    }
    public AppointmentEntity(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
        this.id = UUID.randomUUID().toString();
    }

    public AppointmentEntity(String patientDni, LocalDate date, LocalTime time, String room) {
        this.id = UUID.randomUUID().toString();
        this.patientDni = patientDni;
        this.date = date;
        this.time = time;
        this.room = room;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientDni() {
        return patientDni;
    }

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Appointment toAppointment() {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(this, appointment);
        return appointment;
    }

    public void fromAppointment(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
    }

    @Override
    public int hashCode() {
        return patientDni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && patientDni.equals(((AppointmentEntity) obj).patientDni));
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id='" + id + '\'' +
                ", patientDni='" + patientDni + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", room='" + room + '\'' +
                '}';
    }
}


