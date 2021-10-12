package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

public class SubjectEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private Integer reference;
    private String topic;
    private Integer credits;
    private ClassroomEntity classroom;

    public SubjectEntity() {
        //empty for framework
    }

    public SubjectEntity(Integer reference, String topic, Integer credits, ClassroomEntity classroom) {
        this.reference = reference;
        this.topic = topic;
        this.credits = credits;
        this.classroom = classroom;
        this.id = UUID.randomUUID().toString();
    }

    public Subject toSubject() {
        Subject subject = new Subject();
        BeanUtils.copyProperties(this, subject);
        return subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public ClassroomEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomEntity classroom) {
        this.classroom = classroom;
    }
}
