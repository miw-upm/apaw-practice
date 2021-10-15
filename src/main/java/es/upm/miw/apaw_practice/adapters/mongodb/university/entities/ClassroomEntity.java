package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.Classroom;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class ClassroomEntity {

    @Id
    private String id;
    private String school;
    private Integer number;
    private Integer capacity;

    public ClassroomEntity() {
        //empty for framework
    }

    public ClassroomEntity(Classroom classroom) {
        BeanUtils.copyProperties(classroom, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
