package es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities;

import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class DoctorEntity {
    @Id
    private String id;
    private String nick;
    private String surname;
    private LocalDate activeSince;

    public DoctorEntity() {
        //Empty for framework
    }

    public DoctorEntity(Doctor doctor) {
        BeanUtils.copyProperties(doctor, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(LocalDate activeSince) {
        this.activeSince = activeSince;
    }

    public Doctor toDoctor() {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(this, doctor);
        return doctor;
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "id='" + this.id + '\'' +
                ", nick='" + this.nick + '\'' +
                ", surname='" + this.surname + '\'' +
                ", activeSince=" + this.activeSince +
                '}';
    }
}
