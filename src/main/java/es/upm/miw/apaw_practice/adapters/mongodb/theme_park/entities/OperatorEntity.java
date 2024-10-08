package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


@Document
public class OperatorEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String idEmployee;
    private String nick;
    private LocalDateTime registrationDate;
    @DBRef
    private RideEntity rideEntity;

    public OperatorEntity() {
        //empty for framework
    }

    public OperatorEntity(String idEmployee, String nick, LocalDateTime registrationDate, RideEntity rideEntity) {
        this.id = UUID.randomUUID().toString();
        this.idEmployee = idEmployee;
        this.nick = nick;
        this.registrationDate = registrationDate;
        this.rideEntity = rideEntity;
    }

    public RideEntity getRideEntity() {
        return rideEntity;
    }

    public String getId() {
        return id;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }

    @Override
    public int hashCode() {
        return idEmployee.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (idEmployee.equals(((OperatorEntity) obj).idEmployee));
    }

    @Override
    public String toString() {
        return "OperatorEntity{" +
                "id='" + id + '\'' +
                ", idEmployee='" + idEmployee + '\'' +
                ", nick='" + nick + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", rideEntity='" + rideEntity + '\'' +
                '}';
    }
}
