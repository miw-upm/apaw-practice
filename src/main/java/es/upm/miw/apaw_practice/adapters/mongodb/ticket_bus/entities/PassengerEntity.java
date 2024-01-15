package es.upm.miw.apaw_practice.adapters.mongodb.ticket_bus.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class PassengerEntity {

    @Id
    private String id;
    private String nie;
    private String fullName;
    private Integer number;
    private Boolean accessibility;


    public PassengerEntity(String nie, String fullName, Integer number, Boolean accessibility) {
        this.id = UUID.randomUUID().toString();
        this.nie = nie;
        this.fullName = fullName;
        this.number = number;
        this.accessibility = accessibility;
    }

    public PassengerEntity() {
        this.id = UUID.randomUUID().toString();

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Boolean accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerEntity passenger)) return false;
        return Objects.equals(nie, passenger.nie) &&
                Objects.equals(fullName, passenger.fullName) &&
                Objects.equals(number, passenger.number) &&
                Objects.equals(accessibility, passenger.accessibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nie, fullName, number, accessibility);
    }

    @Override
    public String toString() {
        return "PassengerEntity{" +
                "id='" + id + '\'' +
                ", nie='" + nie + '\'' +
                ", fullName='" + fullName + '\'' +
                ", number=" + number +
                ", accessibility=" + accessibility +
                '}';
    }
}
