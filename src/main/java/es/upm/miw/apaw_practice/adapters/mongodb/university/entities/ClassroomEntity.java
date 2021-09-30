package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class ClassroomEntity {

    @Id
    private String id;
    private Integer number;
    private String school;
    private Integer capacity;

    public ClassroomEntity() {
        //empty for framework
    }

    public ClassroomEntity(Integer number, String school, Integer capacity) {
        this.number = number;
        this.school = school;
        this.capacity = capacity;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
